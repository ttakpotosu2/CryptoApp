package com.example.shared.data.remote

import com.example.shared.data.remote.dto.CoinConverterDto
import com.example.shared.data.remote.dto.CoinEventsDto
import com.example.shared.data.remote.dto.CoinMarketDto
import com.example.shared.data.remote.dto.CoinTickerDto
import com.example.shared.data.remote.dto.CoinsDto
import com.example.shared.data.remote.dto.TodayDto

interface KtorCoinPaprikaApi {
    
    suspend fun getCoins(): List<CoinsDto>
    
    suspend fun getCoinById(coinId: String): com.example.shared.data.remote.dto.CoinDetailDto
    
    suspend fun getCoinToday(coinId: String): List<TodayDto>
    
    suspend fun getCoinTickers(coinId: String): CoinTickerDto
    
    suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto>
    
    suspend fun getCoinEvents(coinId: String): List<CoinEventsDto>
    
    suspend fun getCoinConversion(
        baseCoinId: String,
        quoteCoinId: String,
        amount: Double,
    ): CoinConverterDto
}