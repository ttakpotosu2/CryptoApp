package com.example.shared.domain.use_cases.get_coin

import com.example.shared.common.Resource
import com.example.shared.data.remote.dto.toEvents
import com.example.shared.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.CoinEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import org.koin.core.annotation.Factory

@Factory
class GetCoinEventsUseCase(
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