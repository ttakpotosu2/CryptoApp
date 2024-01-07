package com.example.cryptoapp.presentation.coin_tools.viewmodels

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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CoinToolsViewModel @Inject constructor(
    private val useCase: GetCoinToolsUseCase
) : ViewModel() {
    
    var state by mutableStateOf(ToolScreenState())
    private val numberFormat = DecimalFormat("#0.0000000000")
    
    init {
    	getCoinRates(
            baseCoinId = state.fromID,
            quoteCoinId = state.toId,
            amount = 1.0
        )
    }
    
    fun onEvent(events: ToolsScreenEvents) {
        when (events) {
            is ToolsScreenEvents.NumberButtonItemClicked -> {
                updateValues(keyPressed = events.value)
            }
            is ToolsScreenEvents.SwapIconClicked -> {
                state = state.copy(
                    fromCode = state.toCode,
                    fromID = state.toId,
                    fromValue = state.toValue,
                    fromName = state.toName,
                    toCode = state.fromCode,
                    toId = state.fromID,
                    toValue = state.fromValue,
                    toName = state.fromName
                )
                getCoinRates(
                    baseCoinId = state.fromID,
                    quoteCoinId = state.toId,
                    amount = 1.0
                )
            }
            is ToolsScreenEvents.BottomSheetItemClicked -> {
                if (state.selection == SelectionState.FROM){
                    state = state.copy(
                        fromID = events.value.id,
                        fromName = events.value.name,
                        fromCode = events.value.symbol
                    )
                } else if (state.selection == SelectionState.TO) {
                    state = state.copy(
                        toCode = events.value.symbol,
                        toName = events.value.name,
                        toId = events.value.id
                    )
                }
                //updateValue("")
                getCoinRates(
                    baseCoinId = state.fromID,
                    quoteCoinId = state.toId,
                    amount = if (state.fromValue == "0") 1.0 else state.fromValue.toDouble()
                )
            }
            is ToolsScreenEvents.FromCodeSelect -> {
                state = state.copy(selection = SelectionState.FROM)
            }
            is ToolsScreenEvents.ToCodeSelect -> {
                state = state.copy(selection = SelectionState.TO)
            }
        }
    }
    
    private fun updateValues(keyPressed: String){
        val currentValue = when(state.selection){
            SelectionState.FROM -> state.fromValue
            SelectionState.TO -> state.toValue
        }
        
        val updatedCurrencyValue = when(keyPressed){
            "C" -> "0"
            else -> if (currentValue == "0" || currentValue == "1.0") {
                keyPressed
            } else {
                currentValue + keyPressed
            }
        }
        
        when(state.selection){
            SelectionState.FROM -> {
                val fromValue = updatedCurrencyValue.toDouble()
                val toValue = fromValue * state.price
                state = state.copy(
                    fromValue = updatedCurrencyValue,
                    toValue = numberFormat.format(toValue)
                )
            }
            SelectionState.TO -> {
                val toValue = updatedCurrencyValue.toDouble()
                val fromValue = toValue / state.price
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
        amount: Double,
    ) {
        useCase.getCoinConverter(amount, baseCoinId, quoteCoinId).onEach { result ->
            when(result){
                is Resource.Success -> {
                    val price = result.data?.price ?: 1.0
                    val resultAmount = result.data?.amount ?: 1.0
                    state = ToolScreenState(
                        toId = quoteCoinId,
                        toCode = state.toCode,
                        toName = state.toName,
                        toValue = numberFormat.format(price),
                        price = price / resultAmount,
                        fromID = baseCoinId,
                        fromCode = state.fromCode,
                        fromName = state.fromName,
                    )
                }
                is Resource.Error -> {
                    state = ToolScreenState(
                        error = result.message ?: "Please Try Again"
                    )
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}