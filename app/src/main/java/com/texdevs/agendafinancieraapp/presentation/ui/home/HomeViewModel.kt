package com.texdevs.agendafinancieraapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {
    private val database = Firebase.database
}