package com.texdevs.agendafinancieraapp.domain.model

data class User(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val password: String? = null
)