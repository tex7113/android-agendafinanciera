package com.texdevs.agendafinancieraapp.data.model

import com.texdevs.agendafinancieraapp.domain.model.Expense

data class ExpenseDto (
    val id: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val date: String = "",
    val paid: Boolean = false
) {

    fun toDomain(): Expense = Expense(
        id = id,
        description = description,
        amount = amount,
        date = date,
        paid = paid
    )

    companion object {
        fun fromDomain(expense: Expense): ExpenseDto = ExpenseDto(
            id = expense.id,
            description = expense.description,
            amount = expense.amount,
            date = expense.date,
        )
    }
}