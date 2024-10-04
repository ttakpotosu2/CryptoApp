package com.example.shared.presentation.coin_tools

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.bottomNavBar.CryptoAppBottomNavBar
import com.example.cryptoapp.presentation.coin_tools.components.BottomSheetContent
import com.example.cryptoapp.presentation.coin_tools.components.CurrencyRow
import com.example.cryptoapp.presentation.coin_tools.components.CustomKeyboard
import com.example.cryptoapp.presentation.coin_tools.components.SelectionState
import com.example.cryptoapp.presentation.coin_tools.components.ToolScreenState
import com.example.cryptoapp.presentation.coin_tools.components.ToolsScreenEvents
import com.example.cryptoapp.presentation.coins_list.CoinsListViewModel
import com.example.cryptoapp.presentation.ui.big
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.none
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.veryBig
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CoinToolsScreen(
	coinsListViewModel: CoinsListViewModel = hiltViewModel(),
	navController: NavController,
	state: ToolScreenState,
	onEvents: (ToolsScreenEvents) -> Unit
) {
	val coinListState = coinsListViewModel.state.value
	
	val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C")
	
	val scope = rememberCoroutineScope()
	val sheetState = rememberModalBottomSheetState()
	var showBottomSheet by remember { mutableStateOf(false) }
	
	if (showBottomSheet) {
		ModalBottomSheet(
			sheetState = sheetState,
			onDismissRequest = { showBottomSheet = false },
			dragHandle = {
				Column(
					modifier = Modifier.fillMaxWidth(),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					BottomSheetDefaults.DragHandle()
					Text(text = "Select Currency")
					Spacer(modifier = Modifier.height(medium))
					Divider()
				}
			}
		) {
			BottomSheetContent(
				onClick = { coins ->
					onEvents(ToolsScreenEvents.BottomSheetItemClicked(value = coins))
					scope.launch { sheetState.hide() }.invokeOnCompletion {
						if (!sheetState.isVisible) showBottomSheet = false
					}
				},
				currencies = coinListState.coins
			)
		}
	}
	
	Scaffold(
		bottomBar = {
			Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
			CryptoAppBottomNavBar(
				modifier = Modifier
					.height(80.dp)
					.padding(horizontal = big, vertical = small),
				navController = navController
			)
		}
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.background)
				.padding(paddingValues)
				.padding(medium),
			verticalArrangement = Arrangement.SpaceAround
		) {
			Box(
				contentAlignment = Alignment.CenterStart
			) {
				Column {
					// From Coin Start
					Card(
						shape = RectangleShape
					) {
						Column(
							modifier = Modifier
								.fillMaxWidth()
								.background(MaterialTheme.colorScheme.onBackground)
								.padding(horizontal = medium, vertical = small),
							horizontalAlignment = Alignment.End
						) {
							CurrencyRow(
								code = state.fromCode,
								name = state.fromName,
								modifier = Modifier.fillMaxWidth(),
								onDropDownIconClicked = {
									showBottomSheet = true
									onEvents(ToolsScreenEvents.FromCodeSelect)
								}
							)
							Text(
								text = state.fromValue,
								style = TextStyle(
									fontSize = 40.sp,
									color = if (state.selection == SelectionState.FROM) {
										MaterialTheme.colorScheme.tertiary
									} else {
										MaterialTheme.colorScheme.background
									}
								),
								modifier = Modifier.basicMarquee()
							)
						}
					}
					// From Coin End
					Spacer(modifier = Modifier.height(small))
					//To Coin Start
					Card(
						shape = RectangleShape
					) {
						Column(
							modifier = Modifier
								.fillMaxWidth()
								.background(MaterialTheme.colorScheme.onBackground)
								.padding(horizontal = medium, vertical = small),
							horizontalAlignment = Alignment.End
						) {
							Text(
								text = state.toValue,
								style = TextStyle(
									fontSize = 40.sp,
									color = if (state.selection == SelectionState.TO) {
										MaterialTheme.colorScheme.tertiary
									} else {
										MaterialTheme.colorScheme.background
									}
								),
								modifier = Modifier.basicMarquee()
							)
							CurrencyRow(
								code = state.toCode,
								name = state.toName,
								modifier = Modifier.fillMaxWidth(),
								onDropDownIconClicked = {
									showBottomSheet = true
									onEvents(ToolsScreenEvents.ToCodeSelect)
								}
								
							)
						}
					}
					// T Coin End
				}
				Box(
					modifier = Modifier
						.padding(start = veryBig)
						.rotate(45f)
						.clickable { onEvents(ToolsScreenEvents.SwapIconClicked) }
						.background(MaterialTheme.colorScheme.background)
				) {
					Icon(
						painter = painterResource(id = R.drawable.icon_sync),
						contentDescription = null,
						modifier = Modifier
							.padding(small)
							.size(big),
						tint = MaterialTheme.colorScheme.onBackground
					)
				}
			}
			Spacer(modifier = Modifier.height(small))

			LazyVerticalGrid(
				columns = GridCells.Fixed(3),
				verticalArrangement = Arrangement.spacedBy(none)
			) {
				items(keys) { key ->
					CustomKeyboard(
						modifier = Modifier.aspectRatio(1.1f, matchHeightConstraintsFirst = true),
						key = key,
						onClick = { onEvents(ToolsScreenEvents.NumberButtonItemClicked(key)) },
						backgroundColor = if (key == "C") {
							MaterialTheme.colorScheme.tertiary
						} else {
							MaterialTheme.colorScheme.background
						}
					)
				}
			}
		}
	}
}

/*
    var isExpanded by remember { mutableStateOf(false) }
    val codes = coinListState.coins.map { it.name }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var base by remember { mutableStateOf("usd-us-dollars") }
    var quote by remember { mutableStateOf("btc-bitcoin") }
    var amount by remember { mutableStateOf("100") }

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
 */