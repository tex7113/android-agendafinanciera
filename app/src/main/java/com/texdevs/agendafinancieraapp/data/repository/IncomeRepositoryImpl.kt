package com.texdevs.agendafinancieraapp.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.texdevs.agendafinancieraapp.data.model.IncomeDto
import com.texdevs.agendafinancieraapp.domain.model.Income
import com.texdevs.agendafinancieraapp.domain.repository.IncomeRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    override fun getIncomes(userId: String): Flow<List<Income>> = callbackFlow{
        val ref = database
            .getReference("users")
            .child(userId)
            .child("incomes")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(IncomeDto::class.java)?.toDomain()
                }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}