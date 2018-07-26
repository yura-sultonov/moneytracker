package com.allerria.moneytracker.model.data.datasource.local

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.CurrencyRate

class CurrencyRateCache {
    var currenciesRate: List<CurrencyRate> = listOf(CurrencyRate(Currency.USD, 1.0), CurrencyRate(Currency.RUB, 63.0))
}