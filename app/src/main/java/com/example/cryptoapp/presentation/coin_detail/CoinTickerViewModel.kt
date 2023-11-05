package com.example.cryptoapp.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.common.Constants
import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.domain.use_cases.get_coin.GetCoinTickerUseCase
import com.example.cryptoapp.presentation.coin_detail.components.CoinTickerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinTickerViewModel @Inject constructor(
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