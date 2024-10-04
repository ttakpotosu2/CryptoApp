package com.example.shared.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.example.cryptoapp.presentation.coin_tools.CoinToolsScreen
import com.example.cryptoapp.presentation.coin_tools.components.ToolScreenState
import com.example.cryptoapp.presentation.coin_tools.components.ToolsScreenEvents
import com.example.cryptoapp.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.cryptoapp.presentation.coins_list.CoinsScreen
import com.example.cryptoapp.presentation.home_screen.HomeScreen
import com.example.cryptoapp.presentation.onboarding.OnBoardingScreen
import com.example.cryptoapp.presentation.tools.ToolsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    events: (ToolsScreenEvents) -> Unit,
    state: ToolScreenState
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.OnBoardingScreen.route) {
            OnBoardingScreen(toMainScreen = {
                navHostController.navigate(Screens.CoinsScreen.route)
            })
        }
        composable(Screens.CoinsScreen.route) {
            CoinsScreen(
                toCoinDetailScreen = { id ->
                navHostController.navigate(Screens.CoinDetailScreen.route + "/${id}")
            },
                navController = navHostController
            )
        }
        composable(Screens.CoinDetailScreen.route + "/{coinId}") {
            CoinDetailScreen(navController = navHostController)
        }
        composable(Screens.HomeScreen.route ) {
            HomeScreen(navHostController)
        }
        composable(Screens.ToolsScreen.route ) {
            CoinToolsScreen(navController = navHostController, onEvents = events, state = state)
        }
    }
}