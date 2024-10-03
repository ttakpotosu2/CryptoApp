package com.example.cryptoapp.presentation.coin_detail.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.common.Constants
import com.example.shared.common.Resource
import com.example.shared.domain.use_cases.get_coin.GetCoinTodayUseCase
import com.example.cryptoapp.presentation.coin_detail.components.states.CoinTodayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinTodayDetailViewModel @Inject constructor(
	private val getCoinTodayUseCase: GetCoinTodayUseCase,
	savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CoinTodayState())
    val state: State<CoinTodayState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { getCoinTodayDetail(it)}
    }

    private fun getCoinTodayDetail(coinId: String) {
        getCoinTodayUseCase(coinId).onEach { result ->
            when(result){
                is Resource.Error -> {
                     _state.value = CoinTodayState(error = result.message ?: "An Error has occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinTodayState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = result.data?.let { CoinTodayState(coinToday = it) }!!
                }
            }
        }.launchIn(viewModelScope)
    }
}