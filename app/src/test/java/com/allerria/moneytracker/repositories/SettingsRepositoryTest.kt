package com.allerria.moneytracker.repositories

import android.content.SharedPreferences
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.model.data.repository.SettingsRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class SettingsRepositoryTest {

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var prefs: SharedPreferences
    private val currencyKey = "currency"

    @Before
    fun init() {
        prefs = mock(SharedPreferences::class.java)
        settingsRepository = SettingsRepository(prefs)
    }

    @Test
    fun testGetCurrentCurrency() {
        `when`(prefs.getString(currencyKey, Currency.RUB.toString())).thenReturn(Currency.USD.toString())
        Assert.assertEquals(Currency.USD, settingsRepository.getCurrentCurrency())
        verify(prefs).getString(currencyKey, Currency.RUB.toString())
        verifyNoMoreInteractions(prefs)
    }

    @Test
    fun testGetDefaultCurrency() {
        `when`(prefs.getString(currencyKey, Currency.RUB.toString())).thenReturn(Currency.RUB.toString())
        Assert.assertEquals(Currency.RUB, settingsRepository.getCurrentCurrency())
        verify(prefs).getString(currencyKey, Currency.RUB.toString())
        verifyNoMoreInteractions(prefs)
    }
}