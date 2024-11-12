package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.mutableStateOf
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

        var orderProducts = mutableListOf<Product>()

        init {
            println("==UserOrderRepository: ${orderRepository.getTotalPrice()}")

            orderProducts = orderRepository.getItems().toMutableList()
        }

//        fun getAllItems(): List<Product> = orderRepository.getItems()

//        fun getItemAt(position: Int): Product = orderRepository.getItems()[position]
    }
