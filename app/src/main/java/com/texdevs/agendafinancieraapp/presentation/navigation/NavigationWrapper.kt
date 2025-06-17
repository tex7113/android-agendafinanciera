package com.texdevs.agendafinancieraapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.login.LoginScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.signup.SignupScreen
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.presentation.ui.splash.SplashScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {

    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = Splash){

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
            HomeScreen()
        }
    }
}