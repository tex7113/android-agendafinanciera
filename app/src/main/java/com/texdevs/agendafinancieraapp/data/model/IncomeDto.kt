package com.texdevs.agendafinancieraapp.data.model

import com.texdevs.agendafinancieraapp.domain.model.Income

data class IncomeDto (
    val id: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val date: String = "",
) {
    fun toDomain() = Income(id, description, amount, date)

    companion object {
        fun fromDomain(income: Income) = Income(
            id = income.id,
            description = income.description,
            amount = income.amount,
            date = income.date
        )
    }
}