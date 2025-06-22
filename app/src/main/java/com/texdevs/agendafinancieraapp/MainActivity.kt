package com.texdevs.agendafinancieraapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.texdevs.agendafinancieraapp.data.repository.IncomeRepositoryImpl
import com.texdevs.agendafinancieraapp.presentation.navigation.NavigationWrapper
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeViewModelFactory
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.ui.theme.AgendaFinancieraAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = FirebaseAuth.getInstance()
        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val firebaseDatabase = remember { FirebaseDatabase.getInstance() }
            val incomeRepository = remember { IncomeRepositoryImpl(firebaseDatabase) }
            val addIncomeviewModel: AddIncomeViewModel = viewModel(
                factory = AddIncomeViewModelFactory(incomeRepository)
            )

            navHostController = rememberNavController()
            AgendaFinancieraAppTheme {
                NavigationWrapper(navHostController, auth, authViewModel, addIncomeviewModel)
            }
        }
    }
//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser!=null){
//            //Navigate to home
//            Log.i("Tex","Estoy logado")
//
//            auth.signOut()
//        }
//    }
}
