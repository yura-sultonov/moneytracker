package com.allerria.moneytracker.model.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.allerria.moneytracker.entity.Currency
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val prefs: SharedPreferences) {

    private val currencyKey = "currency"
    private val defaultCurrency = Currency.RUB

    fun getCurrentCurrency(): Currency {
        return when (prefs.getString(currencyKey, defaultCurrency.toString())) {
            Currency.RUB.toString() -> Currency.RUB
            Currency.USD.toString() -> Currency.USD
            Currency.EUR.toString() -> Currency.EUR
            else -> Currency.RUB
        }
    }

    fun setCurrentCurrency(currency: Currency) {
        this.prefs.edit().putString(currencyKey, currency.toString()).apply()
    }
}