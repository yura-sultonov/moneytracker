package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.CurrencyRate
import com.allerria.moneytracker.entity.Money

class MoneyConverter(private val currencyRateManager: CurrencyRateManager) {
    fun convert(money: Money): List<Money> {
        return currencyRateManager.currenciesRate.map { Money(it.currency, convert(money, it.currency).value) }
    }
    fun convert(money: Money, currency: Currency): Money = Money(currency, money.value / (getCurrencyRate(money.currency).value / getCurrencyRate(currency).value))

    private fun getCurrencyRate(currency: Currency): CurrencyRate = currencyRateManager.currenciesRate.find { it.currency == currency }!!
}