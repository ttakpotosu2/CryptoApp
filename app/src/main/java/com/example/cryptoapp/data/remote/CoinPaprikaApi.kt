package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoapp.data.remote.dto.CoinEventsDto
import com.example.cryptoapp.data.remote.dto.CoinMarketDto
import com.example.cryptoapp.data.remote.dto.CoinTickerDto
import com.example.cryptoapp.data.remote.dto.CoinsDto
import com.example.cryptoapp.data.remote.dto.TodayDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinsDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

    @GET("/v1/coins/{coinId}/ohlcv/today")
    suspend fun getCoinToday(@Path("coinId") coinId: String): List<TodayDto>

    @GET("/v1/tickers/{coinId}")
    suspend fun getCoinTickers(@Path("coinId") coinId: String): CoinTickerDto

    @GET("/v1/coins/{coinId}/markets")
    suspend fun getCoinMarkets(@Path("coinId") coinId: String): List<CoinMarketDto>

    @GET("/v1/coins/{coinId}/events")
    suspend fun getCoinEvents(@Path("coinId") coinId: String): List<CoinEventsDto>
}