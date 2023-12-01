package com.example.cryptoapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.cryptoapp.R

data class Page(
    val title: String,
    val subTitle: String,
    val description: String,
    @DrawableRes val image: Int,
    val tag: String = ""
)

val pages = listOf(
    Page(
        title = "Welcome to CryptoApp",
        subTitle = "Explore a New Financial Frontier",
        description = "Welcome to CryptoApp, your gateway to the exciting world of cryptocurrency. " +
                "Get ready to embark on a journey where digital assets meet limitless possibilities. " +
                "Let's dive into the future of finance together!",
        image = R.drawable.image_one
    ),
    Page(
        title = "Secure Your Assets",
        subTitle = "Fort Knox in Your Pocket",
        description = "Security is our priority. Your assets are safeguarded with state-of-the-art encryption " +
                "and decentralized technology. Explore the peace of mind that comes with knowing your " +
                "investments are in safe hands.",
        image = R.drawable.image_two
    ),
    Page(
        title = "Trade with Confidence",
        subTitle = "Unleash Your Trading Potential",
        description = "Whether you're a seasoned trader or just starting, CryptoVerse empowers you to trade " +
                "with confidence. Dive into the dynamic world of cryptocurrency markets and take control " +
                "of your financial destiny.",
        image = R.drawable.image_three
    ),
    Page(
        title = "Stay Informed, Anytime, Anywhere",
        subTitle = "Real-Time Insights at Your Fingertips",
        description = "Stay ahead of the curve with our real-time market updates and personalized insights." +
                " CryptoVerse keeps you informed about the latest trends, news, and opportunities, ensuring " +
                "you never miss a beat in the fast-paced world of cryptocurrencies",
        image = R.drawable.image_four,
        tag = "Get ready to redefine your financial journey with CryptoApp"
    )
)