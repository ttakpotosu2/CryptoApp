package org.koin.ksp.generated

import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.*

public fun KoinApplication.defaultModule(): KoinApplication = modules(defaultModule)
public val defaultModule : Module = module {
	single() { com.example.shared.data.remote.KtorCoinPaprikaApiImpl(client=get()) } bind(com.example.shared.data.remote.KtorCoinPaprikaApi::class)
	single() { com.example.shared.data.repository.CoinRepositoryImpl(api=get()) } bind(com.example.shared.domain.repository.CoinRepository::class)
	factory() { com.example.shared.domain.use_cases.coin_tools.GetCoinToolsUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coin.GetCoinEventsUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coin.GetCoinMarketsUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coin.GetCoinTickerUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coin.GetCoinTodayUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coin.GetCoinUseCase(repository=get()) } 
	factory() { com.example.shared.domain.use_cases.get_coins.GetCoinsUseCase(repository=get()) } 
}