package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.shared.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.shared.presentation.navigation.NavGraph
import com.example.shared.presentation.ui.theme.CryptoAppTheme
import org.koin.androidx.compose.getKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {
                val navController = rememberNavController()
                val viewModel: CoinToolsViewModel = getKoin().get()
                NavGraph(navHostController = navController, events = viewModel::onEvent, state = viewModel.state)
            }
        }
    }
}