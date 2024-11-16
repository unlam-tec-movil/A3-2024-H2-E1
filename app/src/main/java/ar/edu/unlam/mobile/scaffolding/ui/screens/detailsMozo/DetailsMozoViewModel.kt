package ar.edu.unlam.mobile.scaffolding.ui.screens.detailsMozo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import ar.edu.unlam.mobile.scaffolding.domain.products.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class DetailsMozoViewModel
    @Inject
    constructor(
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        var totalPrice = mutableStateOf(0.00)
        var totalItems = mutableStateOf(0)

        private val _orderProducts = MutableStateFlow<List<Product>>(emptyList())
        val orderProducts: StateFlow<List<Product>> = _orderProducts

        fun setOrder(jsonOrder: String) {
            val decodedJson = URLDecoder.decode(jsonOrder, StandardCharsets.UTF_8.toString())
            orderRepository.fromJson(decodedJson)
            updateOrder()
        }

        private fun updateOrder() {
            totalItems.value = orderRepository.getItems().size
            totalPrice.value = orderRepository.getTotalPrice().toDouble()

            viewModelScope.launch {
                _orderProducts.emit(orderRepository.getItems())
            }
        }

        fun clearOrder() {
            orderRepository.removeAllItems()
            orderRepository.clearTable()
        }
    }
