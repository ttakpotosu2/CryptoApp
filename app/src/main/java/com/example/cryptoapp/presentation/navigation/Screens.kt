package com.example.cryptoapp.presentation.navigation

sealed class Screens(val route: String) {
    object CoinsScreen: Screens(route = "coins_screen")
    object CoinDetailScreen: Screens(route = "coin_detail_screen")
}