package com.example.shared.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import com.example.cryptoapp.presentation.ui.theme.Monorama
import com.example.cryptoapp.presentation.ui.verySmall

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = page.title,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.onBackground,
                blendMode = BlendMode.Difference
            )
        )
        Spacer(modifier = Modifier.height(verySmall))
        Text(
            text = page.title,
            modifier = Modifier.padding(small),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontFamily = Monorama,
                fontSize = 46.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = page.subTitle,
            modifier = Modifier.padding(small),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontFamily = Chakrapetch,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal
            )
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(small),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontFamily = Chakrapetch,
                fontSize = 16.sp
            )
        )
        Text(
            text = page.tag,
            modifier = Modifier.padding(small),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontFamily = Chakrapetch,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        )
    }
}