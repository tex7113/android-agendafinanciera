package com.texdevs.agendafinancieraapp.domain.usecase

import com.texdevs.agendafinancieraapp.domain.repository.ExpenseRepository

class GetExpensesUseCase(private val repository: ExpenseRepository) {
    operator fun invoke(userId: String) = repository.getExpenses(userId)
}