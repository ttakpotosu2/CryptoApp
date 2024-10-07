package com.example.shared.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.common.Constants
import com.example.shared.common.Resource
import com.example.shared.domain.use_cases.get_coin.GetCoinMarketsUseCase
import com.example.shared.presentation.states.CoinMarketsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

class CoinMarketsViewModel (
	private val getCoinMarketsUseCase: GetCoinMarketsUseCase,
	savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CoinMarketsState())
    val state: State<CoinMarketsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { getCoinMarkets(it)}
    }

    private fun getCoinMarkets(coinId: String) {
        getCoinMarketsUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Error -> {
                     _state.value = CoinMarketsState(error = result.message ?: "An Error has occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinMarketsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinMarketsState(coinMarkets = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}