package com.texdevs.agendafinancieraapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import com.texdevs.agendafinancieraapp.presentation.model.NavItem

object NavItemList {
    val navItems: List<NavItem> = listOf(
        NavItem(
            label = "Home",
            icon = Icons.Default.Home
        ),
        NavItem(
            label = "Transactions",
            icon = Icons.Default.List
        ),
        NavItem(
            label = "Profile",
            icon = Icons.Default.Person
        )
    )
}