package com.texdevs.agendafinancieraapp.domain.repository

import com.texdevs.agendafinancieraapp.domain.model.Income

interface IncomeRepository {
    suspend fun addIncome(userId: String, income: Income): Result<Unit>
}