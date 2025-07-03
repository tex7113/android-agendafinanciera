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
import com.texdevs.agendafinancieraapp.data.repository.ExpenseRepositoryImpl
import com.texdevs.agendafinancieraapp.data.repository.IncomeRepositoryImpl
import com.texdevs.agendafinancieraapp.data.repository.UserProfileRepositoryImpl
import com.texdevs.agendafinancieraapp.domain.usecase.AddExpenseUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetExpensesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetIncomesUseCase
import com.texdevs.agendafinancieraapp.domain.usecase.GetUserProfileUseCase
import com.texdevs.agendafinancieraapp.presentation.navigation.NavigationWrapper
import com.texdevs.agendafinancieraapp.presentation.ui.addexpense.AddExpenseViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeViewModelFactory
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeViewModelFactory
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

            val userProfileRepository = remember { UserProfileRepositoryImpl(firebaseDatabase) }

            val incomeRepository = remember { IncomeRepositoryImpl(firebaseDatabase) }
            val addIncomeviewModel: AddIncomeViewModel = viewModel(
                factory = AddIncomeViewModelFactory(incomeRepository)
            )
            val expenseRepo = remember { ExpenseRepositoryImpl(firebaseDatabase) }
            val useCase = remember { AddExpenseUseCase(expenseRepo) }
            val expenseViewModel = remember { AddExpenseViewModel(useCase) }

            val HomeViewModel = viewModel<HomeViewModel>(
                factory = HomeViewModelFactory(
                    GetIncomesUseCase(incomeRepository),
                    GetExpensesUseCase(expenseRepo),
                    GetUserProfileUseCase(userProfileRepository)
                )
            )


            navHostController = rememberNavController()
            AgendaFinancieraAppTheme {
                NavigationWrapper(navHostController, auth, authViewModel, addIncomeviewModel, expenseViewModel, HomeViewModel)
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
