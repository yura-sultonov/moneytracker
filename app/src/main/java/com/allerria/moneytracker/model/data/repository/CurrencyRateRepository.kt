package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.CurrencyRate
import com.allerria.moneytracker.model.data.datasource.local.CurrencyRateCache
import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import javax.inject.Inject

class CurrencyRateRepository @Inject constructor(private val currencyRateApi: CurrencyRateApi, private val currencyRateCache: CurrencyRateCache) {
    fun getCurrenciesRateFromApi() = currencyRateApi.getCurrencyRate()

    fun getCurrenciesRateFromCache() = currencyRateCache.currenciesRate

    fun setCurrenciesRateFromCache(currenciesRate: List<CurrencyRate>) {
        currencyRateCache.currenciesRate = currenciesRate
    }
}