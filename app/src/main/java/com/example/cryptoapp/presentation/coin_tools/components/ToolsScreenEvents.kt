package com.example.cryptoapp.presentation.coin_tools.components

sealed class ToolsScreenEvents {
    object FromCodeSelect: ToolsScreenEvents()
    object ToCodeSelect: ToolsScreenEvents()
    object SwapIconClicked: ToolsScreenEvents()
    data class BottomSheetItemClicked(val value: String): ToolsScreenEvents()
    data class NumberButtonItemClicked(val value: String): ToolsScreenEvents()
}