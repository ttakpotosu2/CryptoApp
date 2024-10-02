package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.cryptoapp.presentation.navigation.NavGraph
import com.example.cryptoapp.presentation.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CryptoAppTheme {
                val navController = rememberNavController()
                val viewModel: CoinToolsViewModel = hiltViewModel()
                NavGraph(navHostController = navController, events = viewModel::onEvent, state = viewModel.state)
            }
        }
    }
}