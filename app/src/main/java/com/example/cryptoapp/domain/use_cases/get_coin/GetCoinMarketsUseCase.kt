package com.example.cryptoapp.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.shared.data.remote.dto.toCoinMarket
import com.example.shared.domain.model.CoinMarket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCoinMarketsUseCase @Inject constructor(
    private val repository: CoinRepositoryImpl
) {

    operator fun invoke(coinId: String): Flow<Resource<List<CoinMarket>>> = flow {
        try {
            emit(Resource.Loading())
            val coinMarkets = repository.getCoinMarkets(coinId).map { it.toCoinMarket() }
            emit(Resource.Success(coinMarkets))
        }  catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}