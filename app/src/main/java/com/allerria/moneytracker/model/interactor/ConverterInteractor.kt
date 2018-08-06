package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.CurrencyRate
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ConverterInteractor @Inject constructor(private val currencyRateRepository: CurrencyRateRepository) {
    fun convert(money: Money): List<Money> {
        return currencyRateRepository.getCurrenciesRateFromCache().map { Money(it.currency, convert(money, it.currency).value) }
    }

    fun convert(money: Money, currency: Currency): Money = Money(currency, money.value * (getCurrencyRate(currency).value / getCurrencyRate(money.currency).value))

    fun updateCurrencyRateCache() {
        Timber.d("start updating")
        currencyRateRepository.getCurrenciesRateFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val newCurrenciesRate = it.rates
                    var oldCurrenciesRate = currencyRateRepository.getCurrenciesRateFromCache()
                    oldCurrenciesRate.map {
                        when (it.currency) {
                            Currency.RUB -> it.value = newCurrenciesRate.rUB
                            Currency.USD -> it.value = newCurrenciesRate.uSD
                            Currency.EUR -> it.value = newCurrenciesRate.eUR
                        }
                    }
                    currencyRateRepository.setCurrenciesRateFromCache(oldCurrenciesRate)
                }, {
                    Timber.d(it)
                })
    }

    private fun getCurrencyRate(currency: Currency): CurrencyRate = currencyRateRepository.getCurrenciesRateFromCache().find { it.currency == currency }!!
}