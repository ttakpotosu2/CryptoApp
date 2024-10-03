package com.example.shared.domain.use_cases.coin_tools

import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.data.remote.dto.toCoinConverter
import com.example.shared.domain.model.CoinConverter
import com.example.shared.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinToolsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    fun getCoinConverter(
		amount: Double,
		baseCoinId: String,
		quoteCoinId: String
    ): Flow<Resource<CoinConverter>> = flow {
        try {
            emit(Resource.Loading())
            val coinConverter = repository.getCoinConversion(
                baseCoinId = baseCoinId,
                quoteCoinId = quoteCoinId,
                amount = amount
            ).toCoinConverter()
            emit(Resource.Success(coinConverter))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An Error Occurred!"))
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}