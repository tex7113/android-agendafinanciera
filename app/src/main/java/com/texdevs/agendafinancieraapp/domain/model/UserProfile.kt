package com.texdevs.agendafinancieraapp.domain.model

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val totalMoney: Double = 0.0,
    val moneySaved: Double = 0.0,
)