package com.example.cryptoapp.presentation.coin_tools.components

import com.example.shared.domain.model.Coins

sealed class ToolsScreenEvents {
    object FromCodeSelect: ToolsScreenEvents()
    object ToCodeSelect: ToolsScreenEvents()
    object SwapIconClicked: ToolsScreenEvents()
    data class BottomSheetItemClicked(val value: Coins): ToolsScreenEvents()
    data class NumberButtonItemClicked(val value: String): ToolsScreenEvents()
}