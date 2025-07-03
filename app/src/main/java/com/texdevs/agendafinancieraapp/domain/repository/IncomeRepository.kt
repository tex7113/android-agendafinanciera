package com.texdevs.agendafinancieraapp.domain.repository

import com.texdevs.agendafinancieraapp.domain.model.Income
import kotlinx.coroutines.flow.Flow

interface IncomeRepository {
    suspend fun addIncome(userId: String, income: Income): Result<Unit>

    fun getIncomes(userId: String): Flow<List<Income>>
}