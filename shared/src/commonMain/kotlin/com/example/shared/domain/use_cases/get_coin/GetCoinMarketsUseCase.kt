package com.example.shared.domain.use_cases.get_coin

import com.example.shared.common.Resource
import com.example.shared.data.remote.dto.toCoinMarket
import com.example.shared.domain.model.CoinMarket
import com.example.shared.domain.repository.CoinRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCoinMarketsUseCase (
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<List<CoinMarket>>> = flow {
        try {
            emit(Resource.Loading())
            val coinMarkets = repository.getCoinMarkets(coinId).map { it.toCoinMarket() }
            emit(Resource.Success(coinMarkets))
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}