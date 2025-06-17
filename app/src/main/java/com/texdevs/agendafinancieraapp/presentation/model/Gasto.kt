package com.texdevs.agendafinancieraapp.presentation.model

data class Gasto(
    val id: String = "",
    val nombre: String,
    val monto: Double,
    val fecha: String, // o LocalDate
    val pagado: Boolean
)