package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.remote.dto.CoinConverterDto
import com.example.cryptoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoapp.data.remote.dto.CoinEventsDto
import com.example.cryptoapp.data.remote.dto.CoinMarketDto
import com.example.cryptoapp.data.remote.dto.CoinTickerDto
import com.example.cryptoapp.data.remote.dto.CoinsDto
import com.example.cryptoapp.data.remote.dto.TodayDto
import com.example.shared.common.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

class KtorCoinPaprikaApi(private val client: HttpClient) {
	
	suspend fun getCoins(): List<CoinsDto> {
		return client.get{HttpRoutes.COINS}.body()
	}
	
	suspend fun getCoinById(coinId: String): CoinDetailDto {
		return client.get{ url(HttpRoutes.coin(coinId)) }.body()
	}
	
	suspend fun getCoinToday(coinId: String): List<TodayDto> {
		return client.get{ url(HttpRoutes.coinToday(coinId)) }.body()
	}
	
	suspend fun getCoinTickers(coinId: String): CoinTickerDto {
		return client.get{ url(HttpRoutes.coinTicker(coinId)) }.body()
	}
	
	suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto> {
		return client.get{ url(HttpRoutes.coinMarkets(coinId)) }.body()
	}
	
	suspend fun getCoinEvents(coinId: String): List<CoinEventsDto> {
		return client.get{ url(HttpRoutes.coinEvents(coinId)) }.body()
	}
	
	suspend fun getCoinConversion(
		baseCoinId: String,
		quoteCoinId: String,
		amount: Double,
	): CoinConverterDto {
		return client.get{
			url{
				HttpRoutes.converter(baseCoinId, quoteCoinId, amount)
			}
		}.body()
	}
}