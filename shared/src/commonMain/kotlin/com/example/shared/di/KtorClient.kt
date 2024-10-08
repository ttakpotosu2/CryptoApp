package com.example.shared.di

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
import org.koin.compose.viewmodel.dsl.viewModel
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
	
}

val viewModelModules = module {
	viewModel { CoinDetailViewModel(get(), get())  }
	viewModel { CoinEventsViewModel(get(), get())  }
	viewModel { CoinMarketsViewModel(get(), get())  }
	viewModel { CoinTickerViewModel(get(), get())  }
	viewModel { CoinTodayDetailViewModel(get(), get())  }
	viewModelOf(::CoinsListViewModel)
	viewModelOf(::CoinToolsViewModel)
}