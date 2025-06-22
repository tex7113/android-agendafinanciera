package com.texdevs.agendafinancieraapp.domain.model

data class Income(
    val id: String? = null,
    val description: String? = "",
    val amount: Double = 0.0,
    val date: String = "",
)