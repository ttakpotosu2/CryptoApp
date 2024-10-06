package com.example.cryptoapp.di

import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.shared.data.remote.KtorCoinPaprikaApi
import com.example.shared.data.remote.KtorCoinPaprikaApiImpl
import com.example.shared.data.remote.createHttpClient
import com.example.shared.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideHttpClient() = createHttpClient()
    
    @Provides
    @Singleton
    fun provideApi(httpClient: HttpClient): KtorCoinPaprikaApi {
        return KtorCoinPaprikaApiImpl(httpClient)
    }
    
    @Provides
    @Singleton
    fun provideDataSource(api: KtorCoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}