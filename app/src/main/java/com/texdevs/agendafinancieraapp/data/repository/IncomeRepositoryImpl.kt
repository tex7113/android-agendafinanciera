package com.texdevs.agendafinancieraapp.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.texdevs.agendafinancieraapp.data.model.IncomeDto
import com.texdevs.agendafinancieraapp.domain.model.Income
import com.texdevs.agendafinancieraapp.domain.repository.IncomeRepository
import kotlinx.coroutines.tasks.await
import java.util.UUID

class IncomeRepositoryImpl(
    private val database: FirebaseDatabase
) : IncomeRepository {

    override suspend fun addIncome(userId: String, income: Income): Result<Unit> {
        return try {
            val incomeRef = database
                .getReference("users")
                .child(userId)
                .child("incomes")
                .push()

            val incomeWithId = income.copy(id = incomeRef.key ?: UUID.randomUUID().toString())

            incomeRef.setValue(IncomeDto.fromDomain(incomeWithId)).await()
            Result.success(Unit)
        } catch (e:Exception) {
            Result.failure(e)
        }
    }
}