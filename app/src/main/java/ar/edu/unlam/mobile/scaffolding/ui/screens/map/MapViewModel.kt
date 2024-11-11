package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.usecases.RestaurantUseCases
import ar.edu.unlam.mobile.scaffolding.utils.RestaurantsMock
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@Immutable
sealed interface RestaurantsListState {
    data object Loading : RestaurantsListState

    data class Success(
        val restaurants: List<Restaurant>,
    ) : RestaurantsListState

    data class Error(
        val message: String,
    ) : RestaurantsListState
}

@Immutable
sealed interface UserLocationState {
    data object Loading : UserLocationState

    data class Success(
        val location: Location,
    ) : UserLocationState

    data class Error(
        val message: String,
    ) : UserLocationState
}

data class MapUIState(
    val restaurants: RestaurantsListState = RestaurantsListState.Loading,
    val userLocation: UserLocationState = UserLocationState.Loading,
)

@HiltViewModel
class MapViewModel
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
        private val restaurantUseCases: RestaurantUseCases,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(MapUIState())
        val uiState = _uiState.asStateFlow()

        init {
            insertRestaurantsInDb()
            getUserLocation()
        }

        private fun getUserLocation() {
            viewModelScope.launch {
                val result = requestCurrentLocation(context)
                if (result != null) {
                    _uiState.value = _uiState.value.copy(userLocation = UserLocationState.Success(result))
                    getRestaurants()
                } else {
                    _uiState.value = _uiState.value.copy(userLocation = UserLocationState.Error("User location not found"))
                }
            }
        }

        private fun getRestaurants() {
            viewModelScope.launch {
                restaurantUseCases.getRestaurants().collect { restaurants ->
                    val userLocation = (_uiState.value.userLocation as? UserLocationState.Success)?.location
                    if (restaurants.isNotEmpty() && userLocation != null) {
                        val updateRestaurants =
                            restaurants.map { restaurant ->
                                val restaurantLocation =
                                    Location("").apply {
                                        latitude = restaurant.latitude
                                        longitude = restaurant.longitude
                                    }
                                val distance = userLocation.distanceTo(restaurantLocation)
                                restaurant.copy(calculateDistance = distance / 1000)
                            }
                        _uiState.value = _uiState.value.copy(restaurants = RestaurantsListState.Success(updateRestaurants))
                    } else {
                        _uiState.value = _uiState.value.copy(restaurants = RestaurantsListState.Error("No restaurants found"))
                    }
                }
            }
        }

        @SuppressLint("MissingPermission")
        private suspend fun requestCurrentLocation(context: Context): Location? {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            return suspendCancellableCoroutine { continuation ->
                val locationRequest =
                    LocationRequest
                        .Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                        .setMinUpdateIntervalMillis(5000)
                        .build()

                val locationCallback =
                    object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            if (locationResult.locations.isNotEmpty()) {
                                // Verificar que la continuación no se haya reanudado ya
                                if (!continuation.isCompleted) {
                                    continuation.resume(locationResult.lastLocation)
                                    fusedLocationClient.removeLocationUpdates(this) // Cancelar las actualizaciones
                                }
                            }
                        }
                    }

                // Iniciar las actualizaciones de ubicación
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

                // Asegúrate de que se cancelen las actualizaciones si la corutina se cancela
                continuation.invokeOnCancellation {
                    fusedLocationClient.removeLocationUpdates(locationCallback)
                }
            }
        }

        private fun insertRestaurantsInDb() {
            viewModelScope.launch {
                restaurantUseCases.saveRestaurants(RestaurantsMock.restaurantsList)
            }
        }
    }
