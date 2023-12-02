package com.example.cryptoapp.presentation.coin_detail.components

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
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
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 36.sp,
                fontFamily = Monorama,
            )
        )
        Text(
            text = label,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
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
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Monorama
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "/" + teamMember.position,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(.5f),
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
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    val context = LocalContext.current

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
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Row {
                Column {
                    Text(
                        text = market.exchangeName,
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontFamily = Monorama,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Text(
                        text = market.pair,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = Monorama,
                            color = MaterialTheme.colorScheme.onBackground
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
            if (expanded) {

                val style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = Monorama,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(text = "Trust Score/" + market.trustScore, style = style)
                Text(text = "Adjusted Volume Share/" + market.adjustedVolume24hShare, style = style)
                Text(text = "Price/" + market.quotes.uSD.price, style = style)
                Text(text = "Volume last 24h/" + market.quotes.uSD.volume24h, style = style)
                Text(text = "Last updated/" + market.lastUpdated.substring(0, 10), style = style)
                Spacer(modifier = Modifier.height(small))
                TextButton(
                    onClick = {
                        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(market.marketUrl))
                        context.startActivity(urlIntent)
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(text = "visit website")
                }
                Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
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
                color = MaterialTheme.colorScheme.onBackground,
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
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 12.sp,
                        fontFamily = Chakrapetch,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .clip(RectangleShape)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(horizontal = verySmall),
                )
                Text(
                    text = if (coin.isActive) "active" else "inactive",
                    style = TextStyle(
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = Chakrapetch,
                        color = MaterialTheme.colorScheme.onBackground,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = coin.name,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
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
    val context = LocalContext.current
    Column {
        Text(
            text = event.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Chakrapetch,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = event.date.substring(0, 10),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Chakrapetch,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        if (event.description != ""){
            Text(
                text = event.description,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Chakrapetch,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        TextButton(
            onClick = {
                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(event.link))
                context.startActivity(urlIntent)
            },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            ),
            //modifier = Modifier.padding(ultraSmall)
        ) {
            Text(text = "Click for more")
        }
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.onBackground)
    }
}