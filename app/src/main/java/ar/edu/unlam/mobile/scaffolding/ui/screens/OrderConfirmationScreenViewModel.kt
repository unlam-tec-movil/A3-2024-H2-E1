package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderConfirmationScreenViewModel
    @Inject
    constructor(
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        init {
            println("MESA SELECCIONADA: ${orderRepository.getTable()}")
        }
    }
