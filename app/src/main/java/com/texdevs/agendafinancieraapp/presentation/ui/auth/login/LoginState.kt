package com.texdevs.agendafinancieraapp.presentation.ui.auth.login

data class LoginState (
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
)