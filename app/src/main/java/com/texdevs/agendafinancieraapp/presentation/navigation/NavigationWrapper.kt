package com.texdevs.agendafinancieraapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.login.LoginScreen
import com.texdevs.agendafinancieraapp.presentation.ui.signup.SignupScreen
import com.texdevs.agendafinancieraapp.presentation.ui.splash.SplashScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Splash){

        composable<Splash> {
            SplashScreen(
                navigateToLogin = {navHostController.navigate(Login)},
                navigateToSignUP = {navHostController.navigate((SignUp))}
            )
        }

        composable<Login> {
            LoginScreen()
        }

        composable<SignUp> {
            SignupScreen()
        }

        composable<Home> {
            HomeScreen()
        }
    }
}