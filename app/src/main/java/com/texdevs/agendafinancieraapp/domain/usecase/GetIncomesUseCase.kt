package com.texdevs.agendafinancieraapp.domain.usecase

import com.texdevs.agendafinancieraapp.domain.repository.IncomeRepository

class GetIncomesUseCase (private val repository: IncomeRepository){
    operator fun invoke(userId: String) = repository.getIncomes(userId)
}