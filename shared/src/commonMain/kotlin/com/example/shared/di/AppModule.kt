package com.example.shared.di

import com.example.shared.data.repository.CoinRepositoryImpl
import com.example.shared.domain.repository.CoinRepository
import com.example.shared.domain.use_cases.coin_tools.GetCoinToolsUseCase
import com.example.shared.domain.use_cases.get_coin.GetCoinEventsUseCase
import com.example.shared.domain.use_cases.get_coin.GetCoinMarketsUseCase
import com.example.shared.domain.use_cases.get_coin.GetCoinTickerUseCase
import com.example.shared.domain.use_cases.get_coin.GetCoinTodayUseCase
import com.example.shared.domain.use_cases.get_coin.GetCoinUseCase
import com.example.shared.domain.use_cases.get_coins.GetCoinsUseCase
import com.example.shared.presentation.coin_detail.viewmodels.CoinDetailViewModel
import com.example.shared.presentation.coin_detail.viewmodels.CoinEventsViewModel
import com.example.shared.presentation.coin_detail.viewmodels.CoinMarketsViewModel
import com.example.shared.presentation.coin_detail.viewmodels.CoinTickerViewModel
import com.example.shared.presentation.coin_tools.viewmodels.CoinToolsViewModel
import com.example.shared.presentation.coins_list.CoinsListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import org.koin.dsl.module

val appModule = module {
	// Ktor Network?
	single {
		HttpClient(CIO) {
			install(ContentNegotiation) {
//				json(Json {
//					ignoreUnknownKeys = true
//					prettyPrint = true
//					isLenient = true
//				})
			}
		}
	}
}

val provideRepositoryModule = module {
	single<CoinRepository> { CoinRepositoryImpl(get()) }
}

val provideViewModelModule = module {
	single {
		CoinToolsViewModel(get())
	}
	single {
		CoinDetailViewModel(get())
	}
	single {
		CoinEventsViewModel(get())
	}
	single {
		CoinMarketsViewModel(get())
	}
	single {
		CoinTickerViewModel(get())
	}
	single {
		CoinToolsViewModel(get())
	}
	single {
		CoinsListViewModel(get())
	}
}

val provideUseCases = module {
	single {
		GetCoinToolsUseCase(get())
	}
	single {
		GetCoinEventsUseCase(get())
	}
	single {
		GetCoinTickerUseCase(get())
	}
	single {
		GetCoinTodayUseCase(get())
	}
	single {
		GetCoinMarketsUseCase(get())
	}
	single {
		GetCoinsUseCase(get())
	}
	single {
		GetCoinUseCase(get())
	}
}