package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.data.remote.dto.TeamMember
import com.example.cryptoapp.domain.model.CoinEvents
import com.example.cryptoapp.domain.model.CoinMarket
import com.example.cryptoapp.domain.model.Coins
import com.example.cryptoapp.presentation.ui.theme.Kadwa

@Composable
fun MarketsInfo(
    label: String,
    data: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$$data",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )
        )
    }
}

@Composable
fun CoinMarketsItem(
    market: CoinMarket,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color.LightGray)
            .padding(12.dp)
            .clickable { onItemClick() }
    ) {
        Text(
            text = market.exchangeName,
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.Black
            )
        )
        Text(
            text = market.pair,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }
}

@Composable
fun CoinTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(3.dp)
    ) {
        Text(
            text = tag,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CoinListItem(
    coin: Coins,
    onItemClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .fillMaxWidth()
            .background(Color.White)
            .border(width = Dp.Hairline, color = Color.Black, shape = RoundedCornerShape(6.dp))
            .padding(6.dp)
            .clickable { onItemClick() }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "#" + coin.rank.toString(),
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff434346))
                    .padding(horizontal = 4.dp),
                fontSize = 14.sp
            )
            Text(
                text = coin.symbol,
                style = TextStyle(
                    color = Color.White,fontSize = 14.sp
                ),

                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xff434346))
                    .padding(horizontal = 6.dp),

            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (coin.isActive) "active" else "inactive",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(if (coin.isActive) 0xff294D2D else 0xff4D2929))
                    .padding(horizontal = 6.dp),
                fontSize = 14.sp
            )
        }
        Text(
            text = coin.name,
            style = TextStyle(
                color = Color.Black,
                fontSize = 26.sp,
                fontFamily = Kadwa
            )
        )
    }
}

@Composable
fun CoinEventItem(
    event: CoinEvents
) {
    Column {
        Text(text = event.name)
        Text(text = event.description)
    }
}