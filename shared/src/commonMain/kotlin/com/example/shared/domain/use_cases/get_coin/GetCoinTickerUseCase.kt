package com.example.shared.domain.use_cases.get_coin

import com.example.shared.common.Resource
import com.example.shared.domain.model.CoinTicker
import com.example.shared.domain.repository.CoinRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCoinTickerUseCase (
    private val repository: CoinRepository
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