package com.texdevs.agendafinancieraapp.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.texdevs.agendafinancieraapp.presentation.components.BottomNavBar
import com.texdevs.agendafinancieraapp.presentation.navigation.NavItemList
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.profile.ProfileScreen
import com.texdevs.agendafinancieraapp.presentation.ui.Transactions.TransactionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottonNavScreen(
    navigateToAddIcome: () -> Unit,
    navigateToAddExpense: () -> Unit
){
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Agenda Financiera", fontWeight = FontWeight.Bold)
                }
            )
        },
        bottomBar = {
            BottomNavBar(
                navItemList = NavItemList.navItems,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) {innerPadding ->
        ContentScreen(selectedIndex, innerPadding, navigateToAddIcome, navigateToAddExpense)
    }
}

@Composable
fun ContentScreen(selectedIndex: Int, innerPadding: PaddingValues, navigateToAddIcome: () -> Unit, navigateToAddExpense: () -> Unit) {
    when(selectedIndex) {
        0 -> HomeScreen(navigateToAddIcome = navigateToAddIcome, navigateToAddExpense = navigateToAddExpense)
        1 -> TransactionsScreen()
        2 -> ProfileScreen()
    }
}
