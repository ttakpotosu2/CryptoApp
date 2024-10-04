package com.example.shared.presentation.navigation

sealed class Screens(val route: String) {
    object CoinsScreen: Screens(route = "coins_screen")
    object CoinDetailScreen: Screens(route = "coin_detail_screen")
    object OnBoardingScreen: Screens(route = "onboarding_screen")
    object HomeScreen: Screens(route = "home_screen")
    object ToolsScreen: Screens(route = "tools_screen")
}