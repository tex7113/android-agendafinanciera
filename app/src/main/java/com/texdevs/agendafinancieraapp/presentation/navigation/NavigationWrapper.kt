package com.texdevs.agendafinancieraapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.texdevs.agendafinancieraapp.presentation.ui.BottonNavScreen
import com.texdevs.agendafinancieraapp.presentation.ui.addexpense.AddExpenseScreen
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.login.LoginScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.signup.SignupScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.profile.ProfileScreen
import com.texdevs.agendafinancieraapp.presentation.ui.splash.SplashScreen
import com.texdevs.agendafinancieraapp.presentation.ui.Transactions.TransactionsScreen
import com.texdevs.agendafinancieraapp.presentation.ui.addexpense.AddExpenseViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.addincome.AddIncomeViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    authViewModel: AuthViewModel,
    addIncomeViewModel: AddIncomeViewModel,
    addExpenseViewModel: AddExpenseViewModel,
    homeViewModel: HomeViewModel
    ) {

    val startDestination = remember {
        if (auth.currentUser != null) Menu else Splash
    }

    val uid = auth.currentUser?.uid

    NavHost(navController = navHostController, startDestination = startDestination){

        composable<Splash> {
            SplashScreen(
                navigateToLogin = {navHostController.navigate(Login)},
                navigateToSignUP = {navHostController.navigate((SignUp))}
            )
        }

        composable<Login> {
            LoginScreen(
                navigateToHome = {navHostController.navigate(Home) {
                    popUpTo(Login) { inclusive = true }
                }},
                viewModel = authViewModel
            )
        }

        composable<SignUp> {
            SignupScreen(
                navigateToLogin = {
                    navHostController.navigate(Login)},
                viewModel = authViewModel
            )
        }

        composable<Home> {
            HomeScreen(
                viewModel = homeViewModel,

               navigateToAddIncome = {
                   navHostController.navigate(AddIncome)
               },
                navigateToAddExpense = {
                    navHostController.navigate(addExpense)
               }
            )
        }

        composable<Menu> {
            BottonNavScreen(
                homeViewModel = homeViewModel,

                navigateToAddIncome = {
                    navHostController.navigate(AddIncome)
                },
                navigateToAddExpense = {
                    navHostController.navigate(addExpense)
                }
            )
        }

        composable<Transactions> {
            TransactionsScreen()
        }

        composable<Profile> {
            ProfileScreen()
        }

        composable<AddIncome> {
            AddIncomeScreen(
                viewModel = addIncomeViewModel,
                uid = uid,
                navController = navHostController
            )
        }

        composable<addExpense> {
            AddExpenseScreen(
                viewModel = addExpenseViewModel,
                uid = uid,
                navController = navHostController
            )
        }
    }
}