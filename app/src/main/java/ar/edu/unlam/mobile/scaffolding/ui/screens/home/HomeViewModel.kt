package ar.edu.unlam.mobile.scaffolding.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
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
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(HomeUIState())
        val uiState = _uiState.asStateFlow()

        var searchQuery by mutableStateOf("")

        private var allProducts: List<Product> = listOf()

        var totalPrice = mutableStateOf(0.00)
        var totalItems = mutableStateOf(0)

        var isSnackBarVisible = mutableStateOf(false)

        init {
            insertProductsDB()
            getProducts()
            println("==UserOrderRepository: ${orderRepository.getTotalPrice()}")
            updateSnackBar()
        }

        private fun getProducts() {
            viewModelScope.launch {
                try {
                    productsUseCases.getProducts().collect {
                        allProducts = it
                        _uiState.value = HomeUIState(productsState = ProductsUIState.Success(it))
                        Log.d("HomeViewModel", "Products: $it")
                    }
                } catch (e: Exception) {
                    _uiState.value = HomeUIState(productsState = ProductsUIState.Error(e.message ?: "Error"))
                }
            }
        }

        fun filterProducts(query: String) {
            searchQuery = query
            val filteredProducts =
                if (query.isEmpty()) {
                    allProducts
                } else {
                    allProducts.filter { it.name.contains(query, ignoreCase = true) }
                }
            _uiState.value = HomeUIState(productsState = ProductsUIState.Success(filteredProducts))
        }

        private fun insertProductsDB() {
            viewModelScope.launch {
                productsUseCases.saveProducts(ProductsMocks.productList)
            }
        }

        fun addProduct(product: Product) {
            orderRepository.addItem(product)
            updateSnackBar()
        }

        private fun updateSnackBar() {
            totalItems.value = orderRepository.getItems().size
            totalPrice.value = orderRepository.getTotalPrice().toDouble()
            isSnackBarVisible.value = totalItems.value > 0
        }
    }
