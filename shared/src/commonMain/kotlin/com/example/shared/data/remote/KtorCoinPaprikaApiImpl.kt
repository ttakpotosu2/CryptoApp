package com.example.shared.data.remote

import com.example.shared.data.remote.dto.CoinConverterDto
import com.example.shared.common.HttpRoutes
import com.example.shared.data.remote.dto.CoinEventsDto
import com.example.shared.data.remote.dto.CoinMarketDto
import com.example.shared.data.remote.dto.CoinTickerDto
import com.example.shared.data.remote.dto.CoinsDto
import com.example.shared.data.remote.dto.TodayDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

//interface CoinPaprikaApi {
//	@GET("/v1/coins")
//	suspend fun getCoins(): List<CoinsDto>
//
//	@GET("/v1/coins/{coinId}")
//	suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
//
//	@GET("/v1/coins/{coinId}/ohlcv/today")
//	suspend fun getCoinToday(@Path("coinId") coinId: String): List<TodayDto>
//
//	@GET("/v1/tickers/{coinId}")
//	suspend fun getCoinTickers(@Path("coinId") coinId: String): CoinTickerDto
//
//	@GET("/v1/coins/{coinId}/markets")
//	suspend fun getCoinMarkets(@Path("coinId") coinId: String): List<CoinMarketDto>
//
//	@GET("/v1/coins/{coinId}/events")
//	suspend fun getCoinEvents(@Path("coinId") coinId: String): List<CoinEventsDto>
//
//	@GET("/v1/price-converter")
//	suspend fun getCoinConversion(
//		@Query("base_currency_id") baseCoinId: String,
//		@Query("quote_currency_id") quoteCoinId: String,
//		@Query("amount") amount: Double,
//	): CoinConverterDto
//}

class KtorCoinPaprikaApiImpl(
	private val client: HttpClient
): KtorCoinPaprikaApi {
	
	override suspend fun getCoins(): List<CoinsDto> {
		return client.get{HttpRoutes.COINS}.body()
	}
	
	override suspend fun getCoinById(coinId: String): com.example.shared.data.remote.dto.CoinDetailDto {
		return client.get{ url(HttpRoutes.coin(coinId)) }.body()
	}
	
	override suspend fun getCoinToday(coinId: String): List<TodayDto> {
		return client.get{ url(HttpRoutes.coinToday(coinId)) }.body()
	}
	
	override suspend fun getCoinTickers(coinId: String): CoinTickerDto {
		return client.get{ url(HttpRoutes.coinTicker(coinId)) }.body()
	}
	
	override suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto> {
		return client.get{ url(HttpRoutes.coinMarkets(coinId)) }.body()
	}
	
	override suspend fun getCoinEvents(coinId: String): List<CoinEventsDto> {
		return client.get{ url(HttpRoutes.coinEvents(coinId)) }.body()
	}
	
	override suspend fun getCoinConversion(
		baseCoinId: String,
		quoteCoinId: String,
		amount: Double,
	): CoinConverterDto {
		return client.get{
			url(HttpRoutes.CONVERT )
			parameter("base_currency_id", baseCoinId)
			parameter("quote_currency_id", quoteCoinId)
			parameter("amount", amount)
		}.body()
	}
}