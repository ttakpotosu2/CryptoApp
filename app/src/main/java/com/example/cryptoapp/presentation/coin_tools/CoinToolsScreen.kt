package com.example.cryptoapp.presentation.coin_tools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.common.extensions.isNotNull
import com.example.cryptoapp.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.cryptoapp.presentation.coins_list.CoinsListViewModel
import com.example.cryptoapp.presentation.ui.theme.Monorama

@Composable
fun CoinToolsScreen(
    toolsViewModel: CoinToolsViewModel = hiltViewModel(),
    coinsListViewModel: CoinsListViewModel = hiltViewModel()
) {
    val coinToolsState = toolsViewModel.state.value
    val coinListState = coinsListViewModel.state.value

    //get list of coins for drop down menus, base and quote
    var isExpanded by remember { mutableStateOf(false) }
    val codes = coinListState.coins.map { it.name }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var base by remember { mutableStateOf("usd-us-dollars") }
    var quote by remember { mutableStateOf("btc-bitcoin") }
    var amount by remember { mutableStateOf("100") }

    Column {


        TextField(
            value = base,
            onValueChange = { base = it },
            placeholder = { Text(text = "Base") }
        )
        TextField(
            value = quote,
            onValueChange = { quote = it },
            placeholder = { Text(text = "Quote") }
        )
        TextField(
            value = amount,
            onValueChange = { amount = it },
            placeholder = { Text(text = "Amount") }
        )

        TextButton(
            onClick = {
                toolsViewModel.getCoinConversion(
                    amount = amount.toInt(),
                    baseCoinId = base,
                    quoteCoinId = quote
                )
            }
        ) {
            Text(text = "Convert")
        }

        if (coinToolsState.coinTools.isNotNull()) {
            val data = coinToolsState.coinTools
            Text(
                text = data?.price.toString(),
                style = TextStyle(
                    fontFamily = Monorama,
                    fontSize = 50.sp
                )
            )
        }
    }
}