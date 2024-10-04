package com.example.shared.domain.use_cases.get_coin

import com.example.shared.common.Resource
import com.example.shared.domain.model.Today
import com.example.shared.domain.repository.CoinRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCoinTodayUseCase (
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<List<Today>>> = flow {
        try {
            emit(Resource.Loading())
            val coinToday = repository.getCoinToday(coinId).map { it.toToday() }
            emit(Resource.Success(coinToday))
        
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}