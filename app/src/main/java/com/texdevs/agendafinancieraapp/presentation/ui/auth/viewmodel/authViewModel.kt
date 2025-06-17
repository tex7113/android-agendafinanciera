package com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.texdevs.agendafinancieraapp.domain.model.User
import com.texdevs.agendafinancieraapp.presentation.ui.auth.signup.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun onNameChange(value: String) {
        _signUpState.value = _signUpState.value.copy(name = value)
    }

    fun onEmailChange(value: String) {
        _signUpState.value = _signUpState.value.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        _signUpState.value = _signUpState.value.copy(password = value)
    }

    fun registerUser(onSuccess: () -> Unit) {
        val state = _signUpState.value

        if (state.name.isBlank() || state.email.isBlank() ||
            state.password.isBlank()
        ) {
            _signUpState.value = state.copy(error = "Completa todos los campos")
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid ?: return@addOnCompleteListener
                    val newUser = User(
                        id = uid,
                        name = state.name,
                        email = state.email
                    )

                    firebaseDatabase.getReference("users")
                        .child(uid)
                        .setValue(newUser)
                        .addOnSuccessListener {
                            _signUpState.value = SignUpState()
                            onSuccess()
                        }
                        .addOnFailureListener {
                            _signUpState.value = state.copy(error = "Error guardando datos: ${it.message}")
                        }
                } else {
                    _signUpState.value = state.copy(error = task.exception?.message)
                }
            }
    }
}