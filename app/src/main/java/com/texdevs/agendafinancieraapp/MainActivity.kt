package com.texdevs.agendafinancieraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.texdevs.agendafinancieraapp.presentation.navigation.NavigationWrapper
import com.texdevs.agendafinancieraapp.ui.theme.AgendaFinancieraAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            navHostController = rememberNavController()
            AgendaFinancieraAppTheme {
                NavigationWrapper(navHostController)
            }
        }
    }
}
