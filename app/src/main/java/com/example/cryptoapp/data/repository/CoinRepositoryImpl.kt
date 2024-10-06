package com.example.cryptoapp.data.repository

import com.example.shared.data.remote.KtorCoinPaprikaApi
import com.example.shared.data.remote.dto.CoinConverterDto
import com.example.shared.data.remote.dto.CoinDetailDto
import com.example.shared.data.remote.dto.CoinEventsDto
import com.example.shared.data.remote.dto.CoinMarketDto
import com.example.shared.data.remote.dto.CoinTickerDto
import com.example.shared.data.remote.dto.CoinsDto
import com.example.shared.data.remote.dto.TodayDto
import com.example.shared.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: KtorCoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinsDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

    override suspend fun getCoinToday(coinId: String): List<TodayDto> {
        return api.getCoinToday(coinId)
    }

    override suspend fun getCoinTickers(coinId: String): CoinTickerDto {
        return api.getCoinTickers(coinId)
    }

    override suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto> {
        return api.getCoinMarkets(coinId)
    }

    override suspend fun getCoinEvents(coinId: String): List<CoinEventsDto> {
        return api.getCoinEvents(coinId)
    }

    override suspend fun getCoinConversion(
		baseCoinId: String,
		quoteCoinId: String,
		amount: Double
    ): CoinConverterDto {
        return api.getCoinConversion(
            baseCoinId, quoteCoinId, amount
        )
    }
}