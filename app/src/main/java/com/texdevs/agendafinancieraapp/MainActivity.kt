package com.texdevs.agendafinancieraapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.texdevs.agendafinancieraapp.presentation.navigation.Home
import com.texdevs.agendafinancieraapp.presentation.navigation.NavigationWrapper
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.ui.theme.AgendaFinancieraAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        setContent {
            navHostController = rememberNavController()
            AgendaFinancieraAppTheme {
                NavigationWrapper(navHostController, auth)
            }
        }
    }
}
