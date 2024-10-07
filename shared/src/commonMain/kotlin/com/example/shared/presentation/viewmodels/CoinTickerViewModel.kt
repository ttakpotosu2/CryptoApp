package com.example.shared.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.common.Constants
import com.example.shared.common.Resource
import com.example.shared.domain.use_cases.get_coin.GetCoinTickerUseCase
import com.example.shared.presentation.states.CoinTickerState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

class CoinTickerViewModel (
	private val getCoinTickerUseCase: GetCoinTickerUseCase,
	savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CoinTickerState())
    val state: State<CoinTickerState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { getCoinTicker(it)}
    }

    private fun getCoinTicker(coinId: String) {
        getCoinTickerUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Error -> {
                     _state.value = CoinTickerState(error = result.message ?: "An Error has occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinTickerState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinTickerState(coinTicker = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}