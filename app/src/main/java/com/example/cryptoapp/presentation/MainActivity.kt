package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.CoinFragment
import com.example.cryptoapp.MainFragment
import com.example.cryptoapp.R
import com.example.cryptoapp.ToolsFragment
import com.example.cryptoapp.presentation.bottomNavBar.bottomNavItems
import com.example.cryptoapp.presentation.coin_detail.CoinDetailScreen
import com.example.cryptoapp.presentation.coin_tools.CoinToolsScreen
import com.example.cryptoapp.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.cryptoapp.presentation.coins_list.CoinsScreen
import com.example.cryptoapp.presentation.navigation.NavGraph
import com.example.cryptoapp.presentation.onboarding.OnBoardingScreen
import com.example.cryptoapp.presentation.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        setContent {
//            CryptoAppTheme {
//                val navController = rememberNavController()
//                val viewModel: CoinToolsViewModel = hiltViewModel()
//                NavGraph(navHostController = navController, events = viewModel::onEvent, state = viewModel.state)
//            }
//        }
        setContentView(R.layout.activity_main)
        
        val home = MainFragment()
        val coins = CoinFragment()
        val tools = ToolsFragment()
        
        setCurrentFragment(home)
    }
    
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}