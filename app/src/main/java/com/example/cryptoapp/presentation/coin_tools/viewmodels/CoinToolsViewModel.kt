package com.example.cryptoapp.presentation.coin_tools.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.common.Constants.PARAM_AMOUNT
import com.example.cryptoapp.common.Constants.PARAM_BASE_COIN_ID
import com.example.cryptoapp.common.Constants.PARAM_QUOTE_COIN_ID
import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.domain.use_cases.coin_tools.GetCoinToolsUseCase
import com.example.cryptoapp.presentation.coin_tools.components.CoinToolsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinToolsViewModel @Inject constructor(
    private val useCase: GetCoinToolsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoinToolsState())
    val state: State<CoinToolsState> = _state

    fun getCoinConversion(
        baseCoinId: String,
        quoteCoinId: String,
        amount: Int
    ) {
        val data = useCase.getCoinConverter(amount, baseCoinId, quoteCoinId)
        data.onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CoinToolsState(error = result.message ?: "An Error has occurred")
                }

                is Resource.Loading -> {
                    _state.value = CoinToolsState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinToolsState(coinTools = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
