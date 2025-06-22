package com.texdevs.agendafinancieraapp.presentation.ui.addincome

data class AddIncomeState(
    val description: String = "",
    val amount: String = "",
    val date: String = "",
    val error: String? = null
)
