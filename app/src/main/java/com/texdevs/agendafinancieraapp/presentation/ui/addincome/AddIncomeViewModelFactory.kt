package com.texdevs.agendafinancieraapp.presentation.ui.addincome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.texdevs.agendafinancieraapp.domain.repository.IncomeRepository

class AddIncomeViewModelFactory(
    private val repository: IncomeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddIncomeViewModel::class.java)) {
            return AddIncomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}