package com.example.shared.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.common.Constants
import com.example.shared.common.Resource
import com.example.shared.domain.use_cases.get_coin.GetCoinEventsUseCase
import com.example.shared.presentation.states.CoinEventsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

class CoinEventsViewModel (
	private val getCoinEventsUseCase: GetCoinEventsUseCase,
	savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CoinEventsState())
    val state: State<CoinEventsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { getCoinEvents(it)}
    }

    private fun getCoinEvents(coinId: String) {
        getCoinEventsUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Error -> {
                     _state.value = CoinEventsState(error = result.message ?: "An Error has occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinEventsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinEventsState(coinEvents = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}