package com.texdevs.agendafinancieraapp.presentation.ui.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.texdevs.agendafinancieraapp.domain.repository.ExpenseRepository
import com.texdevs.agendafinancieraapp.domain.usecase.AddExpenseUseCase

class AddExpenseViewModelFactory(
    private val addExpenseUseCase: AddExpenseUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddExpenseViewModel(addExpenseUseCase) as T

    }
}