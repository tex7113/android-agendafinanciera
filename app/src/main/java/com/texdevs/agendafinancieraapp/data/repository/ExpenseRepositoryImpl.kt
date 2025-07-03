package com.texdevs.agendafinancieraapp.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.texdevs.agendafinancieraapp.data.model.ExpenseDto
import com.texdevs.agendafinancieraapp.domain.model.Expense
import com.texdevs.agendafinancieraapp.domain.repository.ExpenseRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class ExpenseRepositoryImpl(
    private val database: FirebaseDatabase
) : ExpenseRepository {
    override fun addExpense(
        userId: String,
        expense: Expense,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        val expenseId = database.reference.push().key ?: UUID.randomUUID().toString()
        val expenseWhitId = expense.copy(id = expenseId)

        database.getReference("users")
            .child(userId)
            .child("expenses")
            .child(expenseId)
            .setValue(ExpenseDto.fromDomain(expenseWhitId))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it.message ?: "Error desconocido") }

    }

    override fun getExpenses(userId: String): Flow<List<Expense>> = callbackFlow {
        val ref = database
            .getReference("users")
            .child(userId)
            .child("expenses")
        val listener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(ExpenseDto::class.java)?.toDomain()
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