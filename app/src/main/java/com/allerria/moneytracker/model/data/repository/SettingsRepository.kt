package com.allerria.moneytracker.model.data.repository

import android.content.Context
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.allerria.moneytracker.entity.Currency
import javax.inject.Inject

class SettingsRepository @Inject constructor(context: Context) {

    private var prefs = getDefaultSharedPreferences(context)

    private val currencyKey = "currency"
    private val defaultCurrency = Currency.RUB

    fun getCurrentCurrency(): Currency {
        return when (this.prefs.getString(currencyKey, defaultCurrency.toString())) {
            Currency.RUB.toString() -> Currency.RUB
            Currency.USD.toString() -> Currency.USD
            Currency.EUR.toString() -> Currency.EUR
            else -> Currency.RUB
        }
    }

    fun setCurrentCurrency(currency: Currency) {
        val editor = this.prefs.edit()
        editor.putString(currencyKey, currency.toString())
        editor.apply()
    }
}