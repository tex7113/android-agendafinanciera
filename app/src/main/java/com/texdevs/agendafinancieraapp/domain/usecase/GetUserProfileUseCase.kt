package com.texdevs.agendafinancieraapp.domain.usecase

import com.texdevs.agendafinancieraapp.domain.model.UserProfile
import com.texdevs.agendafinancieraapp.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow

class GetUserProfileUseCase(private val repository: UserProfileRepository) {
    operator fun invoke (userId: String): Flow<UserProfile> = repository.getUserProfile(userId)
}