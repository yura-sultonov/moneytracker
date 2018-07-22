package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.CurrencyRate

class CurrencyRateManager {
    var currenciesRate: List<CurrencyRate> = listOf(CurrencyRate("USD", 1.0), CurrencyRate("RUB", 60.0))
    fun syncRates() {}
}