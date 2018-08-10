package com.allerria.moneytracker.interactors

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.WalletInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.util.*
import kotlin.collections.ArrayList

@RunWith(JUnit4::class)
class WalletInteractorTest {

    private lateinit var walletInteractor: WalletInteractor
    private val testWallet = Wallet(1, "Test", WalletType.CASH, 123.0, Currency.RUB)
    private val testTransaction = Transaction(0, TransactionType.EXPENSE, "", Currency.RUB, 10.0, 0, GregorianCalendar.getInstance().time)

    private lateinit var converterInteractor: ConverterInteractor

    private lateinit var transactionsRepository: TransactionsRepository

    private lateinit var walletRepository: WalletRepository

    @Before
    fun init() {
        converterInteractor = mock(ConverterInteractor::class.java)
        transactionsRepository = mock(TransactionsRepository::class.java)
        walletRepository = mock(WalletRepository::class.java)
        walletInteractor = WalletInteractor(converterInteractor, transactionsRepository, walletRepository)
    }

    @Test
    fun testGetWallets() {
        doReturn(ArrayList<Wallets>()).`when`(walletRepository).getWallets()
        walletInteractor.getWallets()
        verify(walletRepository).getWallets()
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testGetWallet() {
        doReturn(Wallets.Impl(1, "", WalletType.CASH, Currency.RUB, 1000.0)).`when`(walletRepository).getWalletById(0)
        walletInteractor.getWallet(0)
        verify(walletRepository).getWalletById(0)
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testGetBalance() {
        doReturn(2.0).`when`(walletRepository).getBalance(0)
        doReturn(ArrayList<Money>()).`when`(converterInteractor).convert(Money(Currency.USD, 2.0))
        walletInteractor.getBalance(0)
        verify(converterInteractor).convert(Money(Currency.USD, 2.0))
        verify(walletRepository).getBalance(0)
        verifyNoMoreInteractions(converterInteractor)
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testGetTransactions() {
        doReturn(ArrayList<Transactions>()).`when`(transactionsRepository).getTransactions()
        walletInteractor.getTransactions()
        verify(transactionsRepository).getTransactions()
        verifyNoMoreInteractions(transactionsRepository)
    }

    @Test
    fun testGetTransactionById() {
        doReturn(ArrayList<Transactions>()).`when`(transactionsRepository).getTransactionsWalletId(0)
        walletInteractor.getTransactions(0)
        verify(transactionsRepository).getTransactionsWalletId(0)
        verifyNoMoreInteractions(transactionsRepository)
    }

    @Test
    fun testSetBalance() {
        doNothing().`when`(walletRepository).setBalance(1, 10.0)
        walletInteractor.setBalance(1, 10.0)
        verify(walletRepository).setBalance(1, 10.0)
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testUpdateCurrenciesRate() {
        doNothing().`when`(converterInteractor).updateCurrencyRateCache()
        walletInteractor.updateCurrenciesRate()
        verify(converterInteractor).updateCurrencyRateCache()
        verifyNoMoreInteractions(converterInteractor)
    }

    @Test
    fun testExecuteTransaction() {
        doReturn(0.0).`when`(walletRepository).getBalance(testTransaction.walletId)
        doNothing().`when`(transactionsRepository).addTransaction(testTransaction)
        if (testTransaction.type == TransactionType.EXPENSE)
            doNothing().`when`(walletRepository).setBalance(testTransaction.walletId, -testTransaction.amount)
        else
            doNothing().`when`(walletRepository).setBalance(testTransaction.walletId, testTransaction.amount)
        walletInteractor.executeTransaction(testTransaction)
        verify(walletRepository).getBalance(testTransaction.walletId)
        verify(transactionsRepository).addTransaction(testTransaction)
        if (testTransaction.type == TransactionType.EXPENSE)
            verify(walletRepository).setBalance(testTransaction.walletId, -testTransaction.amount)
        else
            verify(walletRepository).setBalance(testTransaction.walletId, testTransaction.amount)
        verifyNoMoreInteractions(transactionsRepository)
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testAddWallet() {
        doNothing().`when`(walletRepository).addWallet(testWallet)
        walletInteractor.addWallet(testWallet)
        verify(walletRepository).addWallet(testWallet)
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testWipeData() {
        doNothing().`when`(walletRepository).clear()
        walletInteractor.wipeData()
        verify(walletRepository).clear()
        verifyNoMoreInteractions(walletRepository)
    }

    @Test
    fun testGetIncomeCategories() {
        doReturn(ArrayList<String>()).`when`(transactionsRepository).getIncomeCategories()
        walletInteractor.getIncomeCategories()
        verify(transactionsRepository).getIncomeCategories()
        verifyNoMoreInteractions(transactionsRepository)
    }

    @Test
    fun testGetExpenseCategories() {
        doReturn(ArrayList<String>()).`when`(transactionsRepository).getExpenseCategories()
        walletInteractor.getExpenseCategories()
        verify(transactionsRepository).getExpenseCategories()
        verifyNoMoreInteractions(transactionsRepository)
    }
}