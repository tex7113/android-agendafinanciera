package com.texdevs.agendafinancieraapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.texdevs.agendafinancieraapp.presentation.components.BottomNavBar
import com.texdevs.agendafinancieraapp.presentation.navigation.NavItemList
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeScreen
import com.texdevs.agendafinancieraapp.presentation.ui.profile.ProfileScreen
import com.texdevs.agendafinancieraapp.presentation.ui.Transactions.TransactionsScreen
import com.texdevs.agendafinancieraapp.presentation.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottonNavScreen(
    homeViewModel: HomeViewModel,
    navigateToAddIncome: () -> Unit,
    navigateToAddExpense: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    var showOptions by remember { mutableStateOf(false) }

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
        },
        floatingActionButton = {
            Column {
                if (showOptions) {
                    FloatingActionButton(
                        onClick = {
                            showOptions = false
                            navigateToAddIncome()

                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar ingreso")
                    }

                    FloatingActionButton(
                        onClick = {
                            showOptions = false
                            navigateToAddExpense()
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Agregar gasto")
                    }
                }

                FloatingActionButton(onClick = { showOptions = !showOptions }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar")
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(selectedIndex, innerPadding, navigateToAddIncome, navigateToAddExpense, homeViewModel)
    }
}

@Composable
fun ContentScreen(
    selectedIndex: Int,
    innerPadding: PaddingValues,
    navigateToAddIcome: () -> Unit,
    navigateToAddExpense: () -> Unit,
    homeViewModel: HomeViewModel
) {
    when (selectedIndex) {
        0 -> HomeScreen(
            modifier = Modifier.padding(innerPadding),
            navigateToAddIncome = navigateToAddIcome,
            navigateToAddExpense = navigateToAddExpense,
            viewModel = homeViewModel
        )

        1 -> TransactionsScreen()
        2 -> ProfileScreen()
    }
}
