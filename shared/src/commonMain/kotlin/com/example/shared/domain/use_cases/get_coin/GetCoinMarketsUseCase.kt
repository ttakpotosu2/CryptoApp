package com.example.shared.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.remote.dto.CoinMarketDto
import com.example.cryptoapp.data.remote.dto.toCoinMarket
import com.example.shared.domain.model.CoinMarket
import com.example.shared.domain.model.CoinTicker
import com.example.shared.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinMarketsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<List<CoinMarket>>> = flow {
        try {
            emit(Resource.Loading())
            val coinMarkets = repository.getCoinMarkets(coinId).map { it.toCoinMarket() }
            emit(Resource.Success(coinMarkets))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An Error Occurred!"))
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}