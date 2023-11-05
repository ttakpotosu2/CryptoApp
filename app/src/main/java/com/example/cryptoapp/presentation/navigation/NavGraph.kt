package com.example.cryptoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.example.cryptoapp.presentation.coins_list.CoinsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.CoinsScreen.route
    ) {
        composable(Screens.CoinsScreen.route) {
            CoinsScreen(toCoinDetailScreen = { id ->
                navHostController.navigate(Screens.CoinDetailScreen.route + "/${id}")
            })
        }
        composable(Screens.CoinDetailScreen.route + "/{coinId}") {
            CoinDetailScreen()
        }
    }
}