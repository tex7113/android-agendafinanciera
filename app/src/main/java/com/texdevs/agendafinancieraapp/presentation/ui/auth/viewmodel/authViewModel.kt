package com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.texdevs.agendafinancieraapp.domain.model.User
import com.texdevs.agendafinancieraapp.domain.model.UserProfile
import com.texdevs.agendafinancieraapp.presentation.ui.auth.login.LoginState
import com.texdevs.agendafinancieraapp.presentation.ui.auth.signup.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun onLoginEmailChange(value: String) {
        _loginState.value = _loginState.value.copy(email = value)
    }

    fun onLoginPasswordChange(value: String) {
        _loginState.value = _loginState.value.copy(password = value)
    }

    fun loginUser(onSuccess: () -> Unit) {
        val state = _loginState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _loginState.value = state.copy(error = "Completa todos los campos")
            return
        }

        _loginState.value = state.copy(isLoading = true)

        firebaseAuth.signInWithEmailAndPassword(state.email, state.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginState.value = LoginState()
                    onSuccess()
                } else {
                    _loginState.value = state.copy(
                        error = it.exception?.message,
                        isLoading = false
                    )
                }
            }
    }

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
                        profile = UserProfile(
                            name = state.name,
                            email = state.email,
                            password = state.password,
                            totalMoney = 0.0,
                            moneySaved = 0.0
                        )
                    )

                    firebaseDatabase.getReference("users")
                        .child(uid)
                        .setValue(newUser)
                        .addOnSuccessListener {
                            _signUpState.value = SignUpState()
                            onSuccess()
                        }
                        .addOnFailureListener {
                            _signUpState.value =
                                state.copy(error = "Error guardando datos: ${it.message}")
                        }
                } else {
                    _signUpState.value = state.copy(error = task.exception?.message)
                }
            }
    }
}