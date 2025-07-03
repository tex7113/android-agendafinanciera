package com.texdevs.agendafinancieraapp.data.model

import com.texdevs.agendafinancieraapp.domain.model.UserProfile

data class UserProfileDto(
    val name: String = "",
    val email: String = "",
    val totalMoney: Double = 0.0,
    val moneySaved: Double = 0.0
) {
    fun toDomain() : UserProfile = UserProfile(
        name = name,
        email = email,
        totalMoney = totalMoney,
        moneySaved = moneySaved
    )

    companion object {
        fun fromDomain(userProfile: UserProfile) = UserProfileDto(
            userProfile.name,
            userProfile.email,
            userProfile.totalMoney,
            userProfile.moneySaved
        )
    }
}