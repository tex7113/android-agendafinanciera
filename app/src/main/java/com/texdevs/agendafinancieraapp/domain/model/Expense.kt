package com.texdevs.agendafinancieraapp.domain.model

data class Expense(
    val id: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val date: String = "",
    val paid: Boolean = false
)