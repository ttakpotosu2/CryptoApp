package com.example.shared.domain.use_cases.get_coin

import com.example.shared.common.Resource
import com.example.shared.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.CoinTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import org.koin.core.annotation.Factory

@Factory
class GetCoinTickerUseCase (
    private val repository: CoinRepositoryImpl
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinTicker>> = flow {
        try {
            emit(Resource.Loading())
            val coinTicker = repository.getCoinTickers(coinId).toCoinTicker()
            emit(Resource.Success(coinTicker))
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}