package ar.edu.unlam.mobile.scaffolding.ui.screens.details

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import ar.edu.unlam.mobile.scaffolding.domain.products.usecases.ProductsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
    @Inject
    constructor(
        private val productsUseCases: ProductsUseCases,
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        var totalPrice = mutableStateOf(0.00)
        var totalItems = mutableStateOf(0)

        var orderProducts = mutableStateListOf<Product>()

        init {
            println("==UserOrderRepository: ${orderRepository.getTotalPrice()}")
            orderProducts = orderRepository.getItems().toMutableStateList()
            updateSnackBar()
        }

        private fun updateSnackBar() {
            totalItems.value = orderRepository.getItems().size
            totalPrice.value = orderRepository.getTotalPrice().toDouble()
        }

        fun removeItem(id: Int) {
            orderRepository.removeItem(id)
            updateSnackBar()
            val removedItem = orderRepository.getItems()
            orderProducts.clear()
            orderProducts.addAll(removedItem)
        }

//        fun getAllItems(): List<Product> = orderRepository.getItems()

//        fun getItemAt(position: Int): Product = orderRepository.getItems()[position]
    }
