package com.allerria.moneytracker.respositories

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class TransactionRepositoryTests {

    private lateinit var walletRepository: WalletRepository
    private lateinit var transactionsRepository: TransactionsRepository
    private lateinit var appDbHelper: AppDbHelper

    private val testWallet = Wallet(1, "Test", WalletType.CASH, 123.0, Currency.RUB)
    private var testTransaction = Transaction(-1, TransactionType.EXPENSE, "AUTO", Currency.RUB, 123.0, -1, "blahblah", GregorianCalendar().time)

    @Before
    fun load() {
        val appContext = InstrumentationRegistry.getTargetContext()
        appDbHelper = AppDbHelper(appContext)
        walletRepository = WalletRepository(appDbHelper)
        transactionsRepository = TransactionsRepository(appDbHelper)
    }

    @Test
    fun shouldCreateTransaction() {
        walletRepository.clear()
        walletRepository.addWallet(testWallet)
        Assert.assertEquals(walletRepository.getWallets().size, 1)
        transactionsRepository.addTransaction(Transaction(testTransaction.id, testTransaction.type, testTransaction.category, testTransaction.currency, testTransaction.amount, walletRepository.getWallets().first().id, testTransaction.details, testTransaction.date))
        Assert.assertEquals(transactionsRepository.getTransactions().size, 1)
    }

    @Test
    fun shouldAddAndClearWalletsDb() {
        walletRepository.clear()
        walletRepository.addWallet(testWallet)
        Assert.assertEquals(walletRepository.getWallets().size, 1)
        transactionsRepository.addTransaction(Transaction(testTransaction.id, testTransaction.type, testTransaction.category, testTransaction.currency, testTransaction.amount, walletRepository.getWallets().first().id, testTransaction.details, testTransaction.date))
        transactionsRepository.deleteAllByWalletId(walletRepository.getWallets().first().id)
        Assert.assertEquals(transactionsRepository.getTransactions().size, 0)
    }

    @Test
    fun shouldFieldsEqual() {
        walletRepository.clear()
        walletRepository.addWallet(testWallet)
        Assert.assertEquals(walletRepository.getWallets().size, 1)
        transactionsRepository.addTransaction(Transaction(testTransaction.id, testTransaction.type, testTransaction.category, testTransaction.currency, testTransaction.amount, walletRepository.getWallets().first().id, testTransaction.details, testTransaction.date))
        Assert.assertEquals(transactionsRepository.getTransactions().firstOrNull()?.type, testTransaction.type)
        Assert.assertEquals(transactionsRepository.getTransactions().firstOrNull()?.category, testTransaction.category)
        Assert.assertEquals(transactionsRepository.getTransactions().firstOrNull()?.currency, testTransaction.currency)
        Assert.assertEquals(transactionsRepository.getTransactions().firstOrNull()?.amount, testTransaction.amount)
        Assert.assertEquals(transactionsRepository.getTransactions().firstOrNull()?.details, testTransaction.details)
    }
}
