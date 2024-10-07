package com.example.shared.domain.use_cases.coin_tools

import com.example.shared.common.Resource
import com.example.shared.data.remote.dto.toCoinConverter
import com.example.shared.data.repository.CoinRepositoryImpl
import com.example.shared.domain.model.CoinConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import org.koin.core.annotation.Factory

@Factory
class GetCoinToolsUseCase (
    private val repository: CoinRepositoryImpl
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
        } catch (e: IOException){
            emit(Resource.Error("Check Internet Connection!"))
        }
    }.flowOn(Dispatchers.IO)
}