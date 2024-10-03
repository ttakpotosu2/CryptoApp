package com.example.cryptoapp.presentation.coin_tools.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.shared.domain.model.Coins
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Monorama

@Composable
fun CurrencyRow(
	modifier: Modifier = Modifier,
	code: String,
	name: String,
	onDropDownIconClicked: () -> Unit
) {
	Row(
		modifier = modifier.clickable { onDropDownIconClicked() },
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = code,
			style = TextStyle(
				fontSize = 14.sp,
				color = MaterialTheme.colorScheme.background
			)
		)
		IconButton(
			onClick = {onDropDownIconClicked()}) {
			Icon(imageVector = Icons.TwoTone.ArrowDropDown, contentDescription = null)
		}
		Spacer(modifier = Modifier.weight(1f))
		Text(
			text = name,
			style = TextStyle(fontSize = 14.sp, color = MaterialTheme.colorScheme.background)
		)
	}
}

@Composable
fun CustomKeyboard(
	modifier: Modifier = Modifier,
	key: String,
	backgroundColor: Color,
	onClick: (String) -> Unit
) {
	Box(
		modifier = modifier
			.background(backgroundColor)
			.clickable { onClick(key) },
		contentAlignment = Alignment.Center
	) {
		Text(
			text = key,
			style = TextStyle(
				fontSize = 36.sp,
				color = MaterialTheme.colorScheme.onBackground,
				fontFamily = Monorama
			)
		)
	}
}

@Composable
fun BottomSheetContent(
	onClick: (Coins) -> Unit,
	currencies: List<Coins>,
	modifier: Modifier = Modifier
) {
	LazyColumn {
		items(currencies) { coin ->
			Box(
				modifier = modifier
					.fillMaxWidth()
					.clickable { onClick(coin) }
					.padding(small)
			) {
				Text(text = "${coin.symbol}: ${coin.name}")
			}
		}
	}
}