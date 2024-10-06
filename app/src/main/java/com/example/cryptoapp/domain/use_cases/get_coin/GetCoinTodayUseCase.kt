package com.example.cryptoapp.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.Today
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCoinTodayUseCase @Inject constructor(
    private val repository: CoinRepositoryImpl
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