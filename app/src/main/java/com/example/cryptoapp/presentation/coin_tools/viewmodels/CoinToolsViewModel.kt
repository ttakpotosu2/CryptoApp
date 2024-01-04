package com.example.cryptoapp.presentation.coin_tools.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.common.Resource
import com.example.cryptoapp.domain.use_cases.coin_tools.GetCoinToolsUseCase
import com.example.cryptoapp.presentation.coin_tools.components.SelectionState
import com.example.cryptoapp.presentation.coin_tools.components.ToolScreenState
import com.example.cryptoapp.presentation.coin_tools.components.ToolsScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CoinToolsViewModel @Inject constructor(
    private val useCase: GetCoinToolsUseCase,
) : ViewModel() {
    
    var state by mutableStateOf(ToolScreenState())
    
    fun onEvent(events: ToolsScreenEvents) {
        when (events) {
            is ToolsScreenEvents.BottomSheetItemClicked -> {
                if (state.selection == SelectionState.FROM){
                    state = state.copy(
                        fromCode = events.value,
                        fromName = events.value,
                        toCode = events.value,
                        toName = events.value
                    )
                } else if (state.selection == SelectionState.TO) {
                    state = state.copy(
                        toCode = events.value
                    )
                }
                //updateValue("")
                getCoinRates(baseCoinId = state.fromID, quoteCoinId = state.toId, amount = state.amount)
            }
            ToolsScreenEvents.FromCodeSelect -> {
                state = state.copy(selection = SelectionState.FROM)
            }
            ToolsScreenEvents.ToCodeSelect -> {
                state = state.copy(selection = SelectionState.TO)
            }
            ToolsScreenEvents.SwapIconClicked -> {
                state = state.copy(
                    fromCode = state.toCode,
                    fromValue = state.toValue,
                    fromName = state.toName,
                    toCode = state.fromCode,
                    toValue = state.fromValue,
                    toName = state.fromName
                )
            }
            is ToolsScreenEvents.NumberButtonItemClicked -> {
                updateValue(value = events.value)
            }
        }
    }
    
    private fun updateValue(value: String){
        val currentCurrencyValue = when(state.selection){
            SelectionState.FROM -> state.fromValue
            SelectionState.TO -> state.toValue
        }
        val fromCurrencyRate = state.fromValue.toIntOrNull() ?: 0
        val toCurrencyRate = state.toValue.toIntOrNull() ?: 0
        
        val updatedCurrencyValue = when(value){
            "C" -> "1.00"
            else -> if (currentCurrencyValue == "1.00") value else currentCurrencyValue + value
        }
        
        val numberFormat = DecimalFormat("#.00")
        
        when(state.selection){
            SelectionState.FROM -> {
                val fromValue = updatedCurrencyValue.toInt()
                val toValue = fromValue / fromCurrencyRate * toCurrencyRate// TODO: Check if math is correct
                state = state.copy(
                    fromValue = updatedCurrencyValue,
                    toValue = numberFormat.format(toValue)
                )
            }
            SelectionState.TO -> {
                val toValue = updatedCurrencyValue.toInt()
                val fromValue = toValue / toCurrencyRate * fromCurrencyRate
                state = state.copy(
                    toValue = updatedCurrencyValue,
                    fromValue = numberFormat.format(fromValue)
                )
            }
        }
    }
    
    private fun getCoinRates(
        baseCoinId: String,
        quoteCoinId: String,
        amount: Int
    ) {
        val data = useCase.getCoinConverter(amount, baseCoinId, quoteCoinId)
        viewModelScope.launch {
            data.onEach { result ->
                when(result){
                    is Resource.Success -> {
                        state = ToolScreenState() //TODO: Lets keep an eye on this map thing
                    }
                    is Resource.Error -> {
                        state = ToolScreenState(error = result.message)
                    }
                    is Resource.Loading -> {}
                }
            }
        }
        Log.e("test", "$data")
    }
}

    /*
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
*/