package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.CurrencyRate

class CurrencyRateManager {

    var currenciesRate: List<CurrencyRate> = listOf(CurrencyRate(Currency.USD, 1.0), CurrencyRate(Currency.RUB, 60.0))
    fun syncRates() {}
}