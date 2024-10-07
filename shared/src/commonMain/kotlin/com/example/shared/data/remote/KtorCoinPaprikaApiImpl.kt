package com.example.shared.data.remote

import com.example.shared.common.Constants
import com.example.shared.data.remote.dto.CoinConverterDto
import com.example.shared.data.remote.dto.CoinDetailDto
import com.example.shared.data.remote.dto.CoinEventsDto
import com.example.shared.data.remote.dto.CoinMarketDto
import com.example.shared.data.remote.dto.CoinTickerDto
import com.example.shared.data.remote.dto.CoinsDto
import com.example.shared.data.remote.dto.TodayDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.path
import org.koin.core.annotation.Single

@Single
class KtorCoinPaprikaApiImpl (
	private val client: HttpClient
): KtorCoinPaprikaApi {
	override suspend fun getCoins(): List<CoinsDto> {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/coins")
			}
		}.body()
	}
	
	override suspend fun getCoinById(coinId: String): CoinDetailDto {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/coins/$coinId")
			}
		}.body()
	}
	
	override suspend fun getCoinToday(coinId: String): List<TodayDto> {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/coins/$coinId/ohlcv/today")
			}
		}.body()
	}
	
	override suspend fun getCoinTickers(coinId: String): CoinTickerDto {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/tickers/$coinId")
			}
		}.body()
	}
	
	override suspend fun getCoinMarkets(coinId: String): List<CoinMarketDto> {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/coins/$coinId/markets")
			}
		}.body()
	}
	
	override suspend fun getCoinEvents(coinId: String): List<CoinEventsDto> {
		return client.get {
			url {
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/coins/$coinId/events")
			}
		}.body()
	}
	
	override suspend fun getCoinConversion(
		baseCoinId: String,
		quoteCoinId: String,
		amount: Double,
	): CoinConverterDto {
		return client.get{
			url{
				protocol = URLProtocol.HTTPS
				host = Constants.BASE_URL
				path("/v1/price-converter")
			}
			parameter("base_currency_id", baseCoinId)
			parameter("quote_currency_id", quoteCoinId)
			parameter("amount", amount)
		}.body()
	}
}