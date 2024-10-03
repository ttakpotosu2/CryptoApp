package com.example.cryptoapp.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.bottomNavBar.CryptoAppBottomNavBar
import com.example.cryptoapp.presentation.ui.big
import com.example.cryptoapp.presentation.ui.medium
import com.example.cryptoapp.presentation.ui.small
import com.example.cryptoapp.presentation.ui.theme.Chakrapetch
import androidx.compose.ui.layout.Layout
import com.example.shared.Greeting

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
                .padding(all = medium)
        ) {
            Text(text = "Shared: ${Greeting().greet()}")
            Text(
                text = "Your Account",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Chakrapetch
                )
            )
            //Profile image and name and bards icon and notification icon
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hi User",
                    style = TextStyle(
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = Chakrapetch
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.image_one),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .border(shape = CircleShape, width = 2.dp, color = Color.Red)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            //Top four coins as trending
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(medium),
                horizontalArrangement = Arrangement.spacedBy(medium)
            ){
                items(favouritesInfo){ info ->
                    FavouritesCard(
                        title = info.title,
                        detail = info.detail,
                        subDetail = info.subDetail
                    )

                }
            }
            //Royalty
            Row {

            }
            // Inception date
            Row {

            }
            //Buttons
            Row {

            }
        }
    }
}