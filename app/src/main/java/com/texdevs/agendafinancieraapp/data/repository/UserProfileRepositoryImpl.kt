package com.texdevs.agendafinancieraapp.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.texdevs.agendafinancieraapp.data.model.UserProfileDto
import com.texdevs.agendafinancieraapp.domain.model.UserProfile
import com.texdevs.agendafinancieraapp.domain.repository.UserProfileRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserProfileRepositoryImpl(
    private val database: FirebaseDatabase
) : UserProfileRepository {
    override fun getUserProfile(userId: String): Flow<UserProfile> = callbackFlow {
        val ref = database
            .getReference("users")
            .child(userId)
            .child("profile")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val datos = snapshot.getValue(UserProfileDto::class.java)
                if (datos != null) {
                    trySend(datos.toDomain())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}