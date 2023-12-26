package com.example.cryptoapp.presentation.coin_tools

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun CoinToolsScreen(

) {
    //get list of coins for drop down menus, base and quote
    //text field for base coin with amount
    //display of price.

    val base by remember { mutableStateOf("") }
    val quote by remember { mutableStateOf("") }
    val amount by remember { mutableStateOf("") }

    Column {

    }
}