package com.texdevs.agendafinancieraapp.presentation.ui.auth.signup

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val error: String? = null
)