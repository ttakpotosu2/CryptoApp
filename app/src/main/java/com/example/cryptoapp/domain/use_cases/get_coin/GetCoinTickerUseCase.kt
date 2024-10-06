package com.example.cryptoapp.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.CoinTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCoinTickerUseCase @Inject constructor(
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