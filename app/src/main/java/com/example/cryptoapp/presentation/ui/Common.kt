package com.example.cryptoapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import com.example.cryptoapp.presentation.ui.theme.Monorama

@Composable
fun OnBoardingButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.background,
                fontFamily = Monorama
            )
        )
    }
}

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.onBackground,
    unselectedColor: Color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(medium)
                    .background(
                        color = if (page == selectedPage) selectedColor else unselectedColor
                    )
            )
        }
    }
}

@Composable
fun OnboardingNextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        shape = RectangleShape
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Monorama
            )
        )
    }
}