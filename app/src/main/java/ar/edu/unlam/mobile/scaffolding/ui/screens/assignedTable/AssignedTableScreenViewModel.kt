package ar.edu.unlam.mobile.scaffolding.ui.screens.assignedTable

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.UserOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssignedTableScreenViewModel
    @Inject
    constructor(
        private val orderRepository: UserOrderRepository,
    ) : ViewModel() {
        fun onChangeTable(number: Int) {
            orderRepository.assignTable(number)
        }

        fun getTable(): Int = orderRepository.getTable()
    }
