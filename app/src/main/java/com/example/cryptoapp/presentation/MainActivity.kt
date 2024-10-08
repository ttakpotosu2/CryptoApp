package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.presentation.navigation.NavGraph
import com.example.cryptoapp.presentation.ui.theme.CryptoAppTheme
import com.example.shared.presentation.viewmodels.CoinToolsViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            KoinContext {
                CryptoAppTheme {
                    val navController = rememberNavController()
                    val viewModel: CoinToolsViewModel = koinViewModel()
                    
                    NavGraph(navHostController = navController, events = viewModel::onEvent, state = viewModel.state)
                }
            }
        }
    }
}