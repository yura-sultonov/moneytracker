package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.CurrencyRate
import com.allerria.moneytracker.entity.Money

class MoneyConverter(private val currencyRateManager: CurrencyRateManager) {
    fun convert(money: Money): List<Money> {
        return currencyRateManager.currenciesRate.map { Money(it.currency, convert(money, it.currency).value) }
    }
    fun convert(money: Money, currency: String): Money = Money(currency, money.value / (getCurrencyRate("USD").value / getCurrencyRate(currency).value))

    private fun getCurrencyRate(currency: String): CurrencyRate = currencyRateManager.currenciesRate.find { it.currency == currency }!!
}