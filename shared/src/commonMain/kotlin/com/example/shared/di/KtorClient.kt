package com.example.shared.di

import androidx.lifecycle.SavedStateHandle
import com.example.shared.data.remote.KtorCoinPaprikaApiImpl
import com.example.shared.presentation.viewmodels.CoinDetailViewModel
import com.example.shared.presentation.viewmodels.CoinEventsViewModel
import com.example.shared.presentation.viewmodels.CoinMarketsViewModel
import com.example.shared.presentation.viewmodels.CoinTickerViewModel
import com.example.shared.presentation.viewmodels.CoinTodayDetailViewModel
import com.example.shared.presentation.viewmodels.CoinToolsViewModel
import com.example.shared.presentation.viewmodels.CoinsListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

//expect fun getKtorEngine(): HttpClientEngine

val appModule = module {
	single {
		HttpClient {
			install(Logging){
				logger = Logger.SIMPLE
				level = LogLevel.BODY
			}
			install(ContentNegotiation){
				json(Json {
					ignoreUnknownKeys = true
					prettyPrint = true
					isLenient = true
				})
			}
			headers {
				append(HttpHeaders.Accept, "application/json")
			}
		}
	}
	singleOf(::KtorCoinPaprikaApiImpl)
	single { SavedStateHandle }
}

val viewModelModules = module {
	viewModelOf(::CoinDetailViewModel)
	viewModelOf(::CoinEventsViewModel)
	viewModelOf(::CoinMarketsViewModel)
	viewModelOf(::CoinsListViewModel)
	viewModelOf(::CoinTickerViewModel)
	viewModelOf(::CoinTodayDetailViewModel)
	viewModelOf(::CoinToolsViewModel)
}