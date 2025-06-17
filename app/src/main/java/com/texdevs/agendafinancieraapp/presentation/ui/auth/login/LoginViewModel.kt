package com.texdevs.agendafinancieraapp.presentation.ui.auth.login

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.texdevs.agendafinancieraapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LoginViewModel: ViewModel(){
    private fun getUser(): Flow<DataSnapshot> = callbackFlow {

    }

    private fun createUser(user: User){
    }
}