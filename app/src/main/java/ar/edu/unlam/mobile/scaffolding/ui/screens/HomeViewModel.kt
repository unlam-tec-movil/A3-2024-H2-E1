package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import ar.edu.unlam.mobile.scaffolding.domain.products.usecases.ProductsUseCases
import ar.edu.unlam.mobile.scaffolding.utils.ProductsMocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface ProductsUIState {
    data class Success(
        val products: List<Product>,
    ) : ProductsUIState

    data object Loading : ProductsUIState

    data class Error(
        val message: String,
    ) : ProductsUIState
}

data class HomeUIState(
    val productsState: ProductsUIState = ProductsUIState.Loading,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val productsUseCases: ProductsUseCases,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(HomeUIState())
        val uiState = _uiState.asStateFlow()

        init {
            insertProductsDB()
            getProducts()
        }

        private fun getProducts() {
            viewModelScope.launch {
                try {
                    productsUseCases.getProducts().collect {
                        _uiState.value = HomeUIState(productsState = ProductsUIState.Success(it))
                        Log.d("HomeViewModel", "Products: $it")
                    }
                } catch (e: Exception) {
                    _uiState.value = HomeUIState(productsState = ProductsUIState.Error(e.message ?: "Error"))
                }
            }
        }

        private fun insertProductsDB() {
            viewModelScope.launch {
                productsUseCases.saveProducts(ProductsMocks.productList)
            }
        }
    }
