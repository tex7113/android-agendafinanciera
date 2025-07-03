package com.texdevs.agendafinancieraapp.presentation.ui.home

import androidx.compose.animation.core.copy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.texdevs.agendafinancieraapp.domain.usecase.GetExpensesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetIncomesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getIncomes: GetIncomesUseCase,
    private val getExpenses: GetExpensesUseCase,
    private val getUserProfile: GetUserProfileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init { loadDashboard() }

    private fun loadDashboard() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid == null) {
            _state.update { it.copy(error = "Usuario no autenticado", isLoading = false) }
            return
        }

        viewModelScope.launch {
            combine(
                getIncomes(uid),
                getExpenses(uid),
                getUserProfile(uid)
            ) { incomes, expenses, profile ->
                val totalIn = incomes.sumOf { it.amount }
                val totalOut = expenses.sumOf { it.amount }
                // Devuelve el nuevo estado directamente desde combine
                HomeState(
                    userName = profile.name,
                    email = profile.email,
                    total = totalIn - totalOut,
                    pendingExpenses = expenses.filter { !it.paid },
                    isLoading = false // La carga termina aquí
                )
            }
                .onStart { _state.update { it.copy(isLoading = true) } } // Inicia el estado de carga
                .catch { e ->
                    // Maneja el error y actualiza el estado
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { newState ->
                    // Actualiza el estado con el resultado de combine
                    _state.value = newState
                }
        }
    }
}