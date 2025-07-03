package com.texdevs.agendafinancieraapp.presentation.ui.addexpense

data class AddExpenseState(
    val description: String = "",
    val amount: String = "",
    val date: String = "",
    val error: String? = null
)
