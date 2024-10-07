package com.example.cryptoapp.presentation.coins_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.bottomNavBar.CryptoAppBottomNavBar
import com.example.cryptoapp.presentation.coin_detail.components.CoinListItem
import com.example.cryptoapp.presentation.ui.big
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.none
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Monorama
import com.example.shared.presentation.viewmodels.CoinsListViewModel
import com.valentinilk.shimmer.shimmer
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CoinsScreen(
	viewModel: CoinsListViewModel = koinViewModel(),
	toCoinDetailScreen: (String) -> Unit,
	navController: NavController
) {
	Scaffold(
		bottomBar = {
			HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
			CryptoAppBottomNavBar(
				modifier = Modifier
					.height(80.dp)
					.padding(horizontal = big, vertical = small),
				navController = navController
			)
		}
	) { paddingValue ->
		val state = viewModel.state.value
		
		Column(
			modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValue)
                .padding(small),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			
			val currentDate by remember { mutableStateOf(LocalDateTime.now()) }
			val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
			
			// Date and logo
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column {
					Text(
						text = currentDate.format(formatter),
						style = TextStyle(
							color = MaterialTheme.colorScheme.onBackground,
							fontFamily = Monorama,
							fontSize = 32.sp
						)
					)
					Text(
						text = "Ghana",
						style = TextStyle(
							color = MaterialTheme.colorScheme.onBackground,
							fontFamily = Monorama,
							fontSize = 18.sp
						)
					)
				}
				Spacer(modifier = Modifier.weight(1f))
				Image(
					painter = painterResource(id = R.drawable.arrow_down_left),
					contentDescription = null,
					modifier = Modifier
                        .size(66.dp)
                        .offset(x = 30.dp),
					colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
				)
				Image(
					painter = painterResource(id = R.drawable.arrow_up_right),
					contentDescription = null,
					modifier = Modifier.size(66.dp),
					colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
				)
			}
			Text(
				text = "CryptoApp",
				style = TextStyle(
					color = MaterialTheme.colorScheme.onBackground,
					fontSize = 68.sp,
					fontFamily = Monorama
				),
				modifier = Modifier
					.padding(top = medium)
			)
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
			if (state.coins.isNotEmpty()) {
				LazyColumn(
					modifier = Modifier
						.background(MaterialTheme.colorScheme.background),
					contentPadding = PaddingValues(none)
				) {
					items(state.coins) { coin ->
						CoinListItem(
							coin = coin,
							onItemClick = { toCoinDetailScreen(coin.id) }
						)
						Spacer(modifier = Modifier.height(medium))
					}
				}
                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
			}
			if (state.error.isNotBlank()) {
				Text(
					text = state.error,
					color = MaterialTheme.colorScheme.error,
					textAlign = TextAlign.Center,
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 20.dp)
				)
			}
			if (state.isLoading) {
				Box(
					modifier = Modifier
						.fillMaxSize()
						.background(MaterialTheme.colorScheme.background)
						.shimmer(),
					contentAlignment = Alignment.Center
				) {
					// Detail out shapes for loading shimmer
					Column(
						modifier = Modifier.padding(small)
					) {
						Row {
							Box(
								modifier = Modifier
									.size(height = 50.dp, width = 150.dp)
									.background(MaterialTheme.colorScheme.onBackground)
							)
							Box(
								modifier = Modifier
									.size(50.dp)
									.background(MaterialTheme.colorScheme.onBackground)
							)
						}
						Box(
							modifier = Modifier
								.size(height = 50.dp, width = 150.dp)
								.background(MaterialTheme.colorScheme.onBackground)
						)
					}
				}
			}
		}
	}
}

