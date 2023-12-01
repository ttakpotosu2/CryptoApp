package com.example.cryptoapp.presentation.bottomNavBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        label = "Coins",
        icon = Icons.Default.CurrencyBitcoin
    ),
    BottomNavItems(
        label = "Tools",
        icon = Icons.Default.Settings
    )
)

@Composable
fun CryptoAppBottomNavBar(
    modifier: Modifier = Modifier
) {
    val isSelected by remember { mutableStateOf(false) }
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = { /*TODO*/ },
                icon = {
                    Image(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White,
                    selectedIconColor = Color.White
                )
            )
        }
    }
}