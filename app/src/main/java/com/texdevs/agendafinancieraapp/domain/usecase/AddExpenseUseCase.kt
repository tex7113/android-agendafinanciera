package com.texdevs.agendafinancieraapp.domain.usecase

import com.texdevs.agendafinancieraapp.domain.model.Expense
import com.texdevs.agendafinancieraapp.domain.repository.ExpenseRepository

class AddExpenseUseCase(
    private val repository: ExpenseRepository
) {
    operator fun invoke(
        userId: String,
        expense: Expense,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
        ) {
        repository.addExpense(userId, expense, onSuccess, onError)
    }
}