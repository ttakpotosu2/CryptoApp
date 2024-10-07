package com.example.shared.domain.use_cases.get_coins

import com.example.shared.common.Resource
import com.example.shared.data.remote.dto.toCoins
import com.example.shared.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.Coins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import org.koin.core.annotation.Factory

@Factory
class GetCoinsUseCase (
    private val repository: CoinRepositoryImpl
) {

    operator fun invoke(): Flow<Resource<List<Coins>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoins() }
            emit(Resource.Success(coins))
        }  catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}