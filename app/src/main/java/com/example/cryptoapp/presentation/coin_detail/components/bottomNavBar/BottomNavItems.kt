package com.example.cryptoapp.presentation.coin_detail.components.bottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItems(
    val label: String,
    val icon: ImageVector,
    val route: String = ""
)

val bottomNavItems = listOf(
    BottomNavItems(
        label = "Home",
        icon = Icons.Default.Home
    ),
    BottomNavItems(
        label = "Tools",
        icon = Icons.Default.Settings
    )
)