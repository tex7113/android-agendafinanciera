package com.texdevs.agendafinancieraapp.data.repository

import com.google.firebase.auth.AuthResult
import com.texdevs.agendafinancieraapp.domain.models.AuthResult // Lo crearemos después

interface AuthRepository {
    suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult
    // Podrías agregar aquí login, logout, getCurrentUser, etc.
}