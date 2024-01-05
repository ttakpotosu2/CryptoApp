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
import javax.inject.Inject

@HiltViewModel
class CoinToolsViewModel @Inject constructor(
    private val useCase: GetCoinToolsUseCase,
) : ViewModel() {
    
    var state by mutableStateOf(ToolScreenState())
    
    fun onEvent(events: ToolsScreenEvents) {
        when (events) {
            is ToolsScreenEvents.NumberButtonItemClicked -> {
                updateValues(value = events.value)
            }
            is ToolsScreenEvents.SwapIconClicked -> {
                state = state.copy(
                    fromCode = state.toCode,
                    fromValue = "0",
                    fromName = state.toName,
                    toCode = state.fromCode,
                    toValue = "0.0",
                    toName = state.fromName
                )
            }
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
                getCoinRates(baseCoinId = state.fromID, quoteCoinId = state.toId, amount = state.price)
            }
            is ToolsScreenEvents.FromCodeSelect -> {
                state = state.copy(selection = SelectionState.FROM)
            }
            is ToolsScreenEvents.ToCodeSelect -> {
                state = state.copy(selection = SelectionState.TO)
            }
        }
    }
    
    private fun updateValues(value: String){
        val currentCurrencyValue = when(state.selection){
            SelectionState.FROM -> state.fromValue
            SelectionState.TO -> state.toValue
        }
        //val fromCurrencyRate = state.fromValue.toIntOrNull() ?: 0
        //val toCurrencyRate = state.toValue.toIntOrNull() ?: 0
        
        val updatedCurrencyValue = when(value){
            "C" -> "0"
            else -> if (currentCurrencyValue == "0") value else currentCurrencyValue + value
        }
        
      //  val numberFormat = DecimalFormat("#.000000000")
        
        when(state.selection){
            SelectionState.FROM -> {
                val fromValue = updatedCurrencyValue.toInt()
                val toValue = fromValue * state.price// TODO: Check if math is correct
                state = state.copy(
                    fromValue = updatedCurrencyValue,
                    toValue = toValue.toString()
                )
            }
            SelectionState.TO -> {
                val toValue = updatedCurrencyValue.toInt()
                val fromValue = toValue / state.price
                state = state.copy(
                    toValue = updatedCurrencyValue,
                    fromValue = fromValue.toString()
                )
            }
        }
    }
    
    private fun getCoinRates(
        baseCoinId: String,
        quoteCoinId: String,
        amount: Double
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