package com.texdevs.agendafinancieraapp.presentation.ui.addexpense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.texdevs.agendafinancieraapp.domain.model.Expense
import com.texdevs.agendafinancieraapp.domain.repository.ExpenseRepository
import com.texdevs.agendafinancieraapp.domain.usecase.AddExpenseUseCase

class AddExpenseViewModel(
    private val addExpenseUseCase: AddExpenseUseCase
) : ViewModel() {

    var state by mutableStateOf(AddExpenseState())
        private set

    fun onDescriptionChanged(value: String) {
        state = state.copy(description = value)
    }

    fun onAmountChanged(value: String) {
        if (value.toDoubleOrNull() != null || value.isEmpty()) {
            state = state.copy(amount = value)
        }
    }

    fun onDateChanged(value: String) {
        state = state.copy(date = value)
    }

    fun saveExpense(
        userId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val amount = state.amount.toDoubleOrNull()
        if (state.description.isBlank() || amount == null || state.date.isBlank()) {
            onError("Por favor completa todos los campos correctamente")
            return
        }

        val expense = Expense(
            description = state.description,
            amount = amount,
            date = state.date,
            paid = false
        )

        addExpenseUseCase(
            userId = userId,
            expense = expense,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}