package com.example.cryptoapp.domain.use_cases.get_coin

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.remote.dto.TodayDto
import com.example.cryptoapp.domain.model.CoinDetail
import com.example.cryptoapp.domain.model.Today
import com.example.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinTodayUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<List<TodayDto>>> = flow {
        try {
            emit(Resource.Loading())
            val coinToday = repository.getCoinToday(coinId)
            emit(Resource.Success(coinToday))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An Error Occurred!"))
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}