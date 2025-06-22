package com.texdevs.agendafinancieraapp.presentation.model

data class Gasto(
    var id: String = "",
    var nombre: String,
    var monto: Double,
    var fecha: String, // o LocalDate
    var pagado: Boolean
)