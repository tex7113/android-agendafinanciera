package com.texdevs.agendafinancieraapp.domain.repository

import com.texdevs.agendafinancieraapp.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {

    fun getUserProfile (userId: String): Flow<UserProfile>
}