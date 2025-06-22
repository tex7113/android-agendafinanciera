package com.texdevs.agendafinancieraapp.domain.model

data class User(
    val id: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val profile: UserProfile? = UserProfile()
)