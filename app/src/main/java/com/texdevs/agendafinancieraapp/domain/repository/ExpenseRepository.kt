package com.texdevs.agendafinancieraapp.domain.repository

import com.texdevs.agendafinancieraapp.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    fun addExpense(userId: String, expense: Expense, onSuccess: () -> Unit, onError: (String) -> Unit)

    fun getExpenses(userId: String): Flow<List<Expense>>
}