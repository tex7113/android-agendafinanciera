package com.texdevs.agendafinancieraapp.presentation.ui.addincome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.texdevs.agendafinancieraapp.domain.model.Income
import com.texdevs.agendafinancieraapp.domain.repository.IncomeRepository
import kotlinx.coroutines.launch

class AddIncomeViewModel(
    private val repository: IncomeRepository
) : ViewModel() {

    var state by mutableStateOf(AddIncomeState())
        private set

    fun onDescriptionChanged(value: String) {
        state = state.copy(description = value)
    }

    fun onAmountChanged(value: String) {
        state = state.copy(amount = value)
    }

    fun onDateChanged(value: String) {
        state = state.copy(date = value)
    }

    fun saveIncome(userId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val income = Income(
            description = state.description,
            amount = state.amount.toDouble(),
            date = state.date
        )

        viewModelScope.launch {
            val result = repository.addIncome(userId, income)
            result
                .onSuccess { onSuccess() }
                .onFailure { onError(it.message ?: "Error desconocido") }
        }
    }
}