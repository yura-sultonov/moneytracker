package com.allerria.moneytracker.interactors

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.model.data.repository.SettingsRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.InfoInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.util.*

@RunWith(JUnit4::class)
class InfoInteractorTest {

    private lateinit var converterInteractor: ConverterInteractor
    private lateinit var transactionsRepository: TransactionsRepository
    private lateinit var walletRepository: WalletRepository
    private lateinit var settingsRepository: SettingsRepository

    private lateinit var infoInteractor: InfoInteractor

    private val testWallet = Wallets.Impl(0, "", WalletType.CASH, Currency.RUB, 0.0)
    private val testTransaction = Transactions.Impl(0, TransactionType.EXPENSE, "", Currency.RUB, 0.0, 0, Calendar.getInstance())

    @Before
    fun init() {
        converterInteractor = mock(ConverterInteractor::class.java)
        transactionsRepository = mock(TransactionsRepository::class.java)
        walletRepository = mock(WalletRepository::class.java)
        settingsRepository = mock(SettingsRepository::class.java)

        infoInteractor = InfoInteractor(converterInteractor, transactionsRepository, walletRepository, settingsRepository)
    }

    @Test
    fun testGetSumOfBalance() {
        doReturn(listOf(testWallet)).`when`(walletRepository).getWallets()
        doReturn(Currency.RUB).`when`(settingsRepository).getCurrentCurrency()
        doReturn(Money(Currency.RUB, 0.0)).`when`(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        infoInteractor.getSumOfBalances()
        verify(walletRepository).getWallets()
        verify(settingsRepository).getCurrentCurrency()
        verify(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        verifyNoMoreInteractions(walletRepository)
        verifyNoMoreInteractions(settingsRepository)
        verifyNoMoreInteractions(converterInteractor)
    }

    @Test
    fun testGetSignOfCurrency() {
        doReturn(Currency.RUB).`when`(settingsRepository).getCurrentCurrency()
        infoInteractor.getSignOfCurrentCurrency()
        verify(settingsRepository).getCurrentCurrency()
        verifyNoMoreInteractions(settingsRepository)
    }

    @Test
    fun testGetSumOfMonthExpense() {
        doReturn(arrayListOf(testTransaction)).`when`(transactionsRepository).getTransactionsByType(TransactionType.EXPENSE)
        doReturn(Currency.RUB).`when`(settingsRepository).getCurrentCurrency()
        doReturn(Money(Currency.RUB, 0.0)).`when`(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        infoInteractor.getSumOfMonthExpense()
        verify(transactionsRepository).getTransactionsByType(TransactionType.EXPENSE)
        verify(settingsRepository).getCurrentCurrency()
        verify(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        verifyNoMoreInteractions(transactionsRepository)
        verifyNoMoreInteractions(settingsRepository)
        verifyNoMoreInteractions(converterInteractor)
    }

    @Test
    fun testGetSumOfMonthIncome() {
        doReturn(arrayListOf(testTransaction)).`when`(transactionsRepository).getTransactionsByType(TransactionType.INCOME)
        doReturn(Currency.RUB).`when`(settingsRepository).getCurrentCurrency()
        doReturn(Money(Currency.RUB, 0.0)).`when`(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        infoInteractor.getSumOfMonthIncome()
        verify(transactionsRepository).getTransactionsByType(TransactionType.INCOME)
        verify(settingsRepository).getCurrentCurrency()
        verify(converterInteractor).convert(Money(Currency.RUB, 0.0), Currency.RUB)
        verifyNoMoreInteractions(transactionsRepository)
        verifyNoMoreInteractions(settingsRepository)
        verifyNoMoreInteractions(converterInteractor)
    }
}