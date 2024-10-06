package com.example.cryptoapp.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.shared.data.remote.dto.toEvents
import com.example.shared.domain.model.CoinEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCoinEventsUseCase @Inject constructor(
    private val repository: CoinRepositoryImpl
) {

    operator fun invoke(coinId: String): Flow<Resource<List<CoinEvents>>> = flow {
        try {
            emit(Resource.Loading())
            val coinEvents = repository.getCoinEvents(coinId).map { it.toEvents() }
            emit(Resource.Success(coinEvents))
        }  catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}