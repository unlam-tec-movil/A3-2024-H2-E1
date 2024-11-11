package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.restaurants.models.Restaurant
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    onBackClick: () -> Unit,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val uiState: MapUIState by viewModel.uiState.collectAsState()

    val isLoading =
        when {
            uiState.restaurants is RestaurantsListState.Loading -> true
            uiState.userLocation is UserLocationState.Loading -> true
            else -> false
        }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            if (uiState.restaurants is RestaurantsListState.Success && uiState.userLocation is UserLocationState.Success) {
                val userLocation = (uiState.userLocation as UserLocationState.Success).location
                val restaurants = (uiState.restaurants as RestaurantsListState.Success).restaurants
                Column(modifier = Modifier.fillMaxSize()) {
                    MapView(restaurants, Modifier.weight(1f).fillMaxWidth(), userLocation)
                    RestaurantsList(restaurants)
                }
            } else {
                Text("Error loading data", modifier = Modifier.align(Alignment.Center))
            }
        }
        BackButton(
            onBackClick = onBackClick,
            modifier = Modifier.align(Alignment.TopStart).padding(8.dp),
        )
    }
}

@Composable
fun MapView(
    restaurantsState: List<Restaurant>,
    modifier: Modifier = Modifier,
    userLocation: Location?,
) {
    GoogleMap(
        modifier = modifier,
        cameraPositionState =
            rememberCameraPositionState {
                if (userLocation != null) {
                    position = CameraPosition.fromLatLngZoom(LatLng(userLocation.latitude, userLocation.longitude), 10f)
                }
            },
        properties = MapProperties(isMyLocationEnabled = true),
    ) {
        restaurantsState.forEach { restaurant ->
            Marker(
                state = MarkerState(position = LatLng(restaurant.latitude, restaurant.longitude)),
                title = restaurant.address,
            )
        }
    }
}

@Composable
fun RestaurantsList(restaurants: List<Restaurant>) {
    Box(
        Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp),
    ) {
        LazyColumn {
            items(restaurants.size) { index ->
                RestaurantItem(restaurants[index])
            }
        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.location_icon),
            contentDescription = restaurant.address,
            modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = restaurant.address,
            color = Color.Black,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "%.1f km".format(restaurant.calculateDistance),
            color = Color(0xFF939393),
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun BackButton(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedButton(
        onClick = onBackClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(100),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = "back",
            modifier = Modifier.size(12.dp),
        )
    }
}
