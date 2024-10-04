package com.example.shared.common

object Constants {

    const val BASE_URL = "https://api.coinpaprika.com/"
    const val PARAM_COIN_ID = "coinId"
    const val PARAM_BASE_COIN_ID = "base_coin_id"
    const val PARAM_QUOTE_COIN_ID = "quote_coin_id"
    const val PARAM_AMOUNT = "amount"
}

object HttpRoutes {
    private const val BASE_URL = "https://api.coinpaprika.com"
    const val COINS = "$BASE_URL/v1/coins"
    fun coin(id: String) = "$BASE_URL/v1/coins/{$id}"
    fun coinToday(id: String) = "$BASE_URL/v1/coins/{$id}/ohlcv/today"
    fun coinTicker(id: String) = "$BASE_URL/v1/tickers/{$id}"
    fun coinMarkets(id: String) = "$BASE_URL/v1/coins/{$id}/markets"
    fun coinEvents(id: String) = "$BASE_URL/v1/coins/{$id}/events"
    const val CONVERT = "$BASE_URL/v1/price-converter"
}