package com.texdevs.agendafinancieraapp.presentation.ui.home

import com.texdevs.agendafinancieraapp.domain.model.Expense

data class HomeState(
    val userName: String = "",
    val email: String = "",
    val moneySaved: Double = 0.0,
    val total: Double = 0.0,
    val pendingExpenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)