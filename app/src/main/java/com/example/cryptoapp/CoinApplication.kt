package com.example.cryptoapp

import android.app.Application
import com.example.shared.di.appModule
import com.example.shared.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule

class CoinApplication: Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidContext(this@CoinApplication)
			androidLogger()
			modules(appModule, viewModelModules, defaultModule)
		}
	}
}