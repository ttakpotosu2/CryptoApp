package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.data.remote.dto.TeamMember
import com.example.cryptoapp.domain.model.CoinEvents
import com.example.cryptoapp.domain.model.CoinMarket
import com.example.cryptoapp.domain.model.Coins
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import com.example.cryptoapp.presentation.ui.theme.Monorama
import com.example.cryptoapp.presentation.ui.verySmall

@Composable
fun MarketsInfo(
    label: String,
    data: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {

        Text(
            text = "$$data/",
            style = TextStyle(
                color = Color.Black,
                fontSize = 36.sp,
                fontFamily = Monorama,
            )
        )
        Text(
            text = label,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = Monorama
            ),
            modifier = Modifier.offset(y = -5.dp)
        )
    }
}

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {

        Text(
            text = teamMember.name,
            style = TextStyle(
                fontSize = 28.sp,
                color = Color.Black,
                fontFamily = Monorama
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "/"+teamMember.position,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black.copy(.5f),
                fontFamily = Monorama
            ),
            modifier = Modifier.offset(y = (-2).dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinMarketsItem(
    market: CoinMarket
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = { expanded = !expanded },
        shape = RectangleShape
    ) {
        Column (
            modifier = Modifier.background(Color.White)
        ){
            Row {
                Column {
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f)
                        .rotate(rotation),
                    onClick = { expanded = !expanded }
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            }
            if (expanded){
                Text(text = "Trust Score/"+ market.trustScore)
                Text(text = "Adjusted Volume Share/"+market.adjustedVolume24hShare)
                Text(text = "Price/"+market.quotes.uSD.price)
                Text(text = "Volume last 24h/"+market.quotes.uSD.volume24h)
                Text(text = "Last updated/"+market.lastUpdated)
                Spacer(modifier = Modifier.height(small))
                TextButton(
                    onClick = { /*TODO*/ },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "visit website")
                }
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun CoinTag(
    tag: String
) {
    Box {
        Text(
            text = "/$tag",
            style = TextStyle(
                color = Color.Black,
                fontFamily = Monorama,
                fontSize = 16.sp
            )
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
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(medium)
        ) {
            Column(
                modifier = Modifier.width(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "#" + coin.rank.toString(),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = Chakrapetch,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .clip(RectangleShape)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(horizontal = verySmall),
                )
                Text(
                    text = if (coin.isActive) "active" else "inactive",
                    style = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = Chakrapetch,
                        color = Color.Black,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = coin.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 48.sp,
                    fontFamily = Monorama
                ),
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(small))
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