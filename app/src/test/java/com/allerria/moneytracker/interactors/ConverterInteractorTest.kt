package com.allerria.moneytracker.interactors

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.CurrencyRate
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class ConverterInteractorTest {

    private lateinit var converterInteractor: ConverterInteractor

    private lateinit var currencyRateRepository: CurrencyRateRepository

    private val testMoney = Money(Currency.RUB, 0.0)

    @Before
    fun init() {
        currencyRateRepository = mock(CurrencyRateRepository::class.java)
        converterInteractor = ConverterInteractor(currencyRateRepository)
    }

    @Test
    fun testConvert() {
        doReturn(ArrayList<CurrencyRate>()).`when`(currencyRateRepository).getCurrenciesRateFromCache()
        converterInteractor.convert(testMoney)
        verify(currencyRateRepository).getCurrenciesRateFromCache()
        verifyNoMoreInteractions(currencyRateRepository)
    }

    @Test
    fun testConvertByCurrency() {
        doReturn(ArrayList<CurrencyRate>()).`when`(currencyRateRepository).getCurrenciesRateFromCache()
        converterInteractor.convert(testMoney)
        verify(currencyRateRepository).getCurrenciesRateFromCache()
        verifyNoMoreInteractions(currencyRateRepository)
    }

    @Test
    fun testGetCurrencyRate() {
        doReturn(listOf<CurrencyRate>(CurrencyRate(Currency.RUB, 20.0))).`when`(currencyRateRepository).getCurrenciesRateFromCache()
        converterInteractor.getCurrencyRate(Currency.RUB)
        verify(currencyRateRepository).getCurrenciesRateFromCache()
        verifyNoMoreInteractions(currencyRateRepository)
    }
}