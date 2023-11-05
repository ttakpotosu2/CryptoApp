package com.example.cryptoapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.data.remote.dto.TeamMember


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
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$$data",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp
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
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            fontStyle = FontStyle.Italic
        )
    }
}