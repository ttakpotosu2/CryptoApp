package com.example.cryptoapp

import android.app.Application
import com.example.shared.di.provideRepositoryModule
import com.example.shared.di.provideUseCases
import com.example.shared.di.provideViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoinApplication: Application(){
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@CoinApplication)
			androidLogger()
			modules(
				provideRepositoryModule, provideViewModelModule, provideUseCases
			)
		}
	}
}