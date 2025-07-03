package com.texdevs.agendafinancieraapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.texdevs.agendafinancieraapp.domain.usecase.GetExpensesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetIncomesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetUserProfileUseCase

class HomeViewModelFactory(
    private val getIncomes: GetIncomesUseCase,
    private val getExpenses: GetExpensesUseCase,
    private val getUseProfile: GetUserProfileUseCase
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getIncomes, getExpenses, getUseProfile) as T
    }
}