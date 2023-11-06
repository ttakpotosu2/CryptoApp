package com.example.cryptoapp.domain.repository

import com.example.cryptoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoapp.data.remote.dto.CoinEvents
import com.example.cryptoapp.data.remote.dto.CoinMarketDto
import com.example.cryptoapp.data.remote.dto.CoinTickerDto
import com.example.cryptoapp.data.remote.dto.CoinsDto
import com.example.cryptoapp.data.remote.dto.TodayDto
import com.example.cryptoapp.domain.model.Today

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
    suspend fun getCoinToday(coinId: String): List<TodayDto>
    suspend fun getCoinTickers(coinId: String): CoinTickerDto
    suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto>
    suspend fun getCoinEvents(coinId: String): List<CoinEvents>
}