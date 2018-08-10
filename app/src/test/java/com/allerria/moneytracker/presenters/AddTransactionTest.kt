package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.transaction.AddTransactionPresenter
import com.allerria.moneytracker.ui.main.transaction.AddTransactionView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import ru.terrakok.cicerone.Router
import java.util.*

@RunWith(JUnit4::class)
class AddTransactionTest {

    private lateinit var addTransactionPresenter: AddTransactionPresenter

    private lateinit var walletInteractor: WalletInteractor
    private lateinit var router: Router
    private lateinit var addTransactionView: AddTransactionView

    private val testTransaction = Transaction(0, TransactionType.EXPENSE, "", Currency.RUB, 10.0, 0, GregorianCalendar.getInstance().time)

    @Before
    fun init() {
        router = mock(Router::class.java)
        addTransactionView = mock(AddTransactionView::class.java)
        walletInteractor = mock(WalletInteractor::class.java)

        addTransactionPresenter = AddTransactionPresenter(walletInteractor, router)
        addTransactionPresenter.attachView(addTransactionView)
    }

    @Test
    fun testInitWallets() {
        Mockito.doReturn(listOf<Wallets>()).`when`(walletInteractor).getWallets()
        Mockito.doNothing().`when`(addTransactionView).setWallets(listOf())
        addTransactionPresenter.initWallets()
        Mockito.verify(addTransactionView).setWallets(listOf())
        Mockito.verify(walletInteractor).getWallets()
        Mockito.verifyNoMoreInteractions(walletInteractor)
        Mockito.verifyNoMoreInteractions(addTransactionView)
    }

    @Test
    fun testAddTransaction() {
        doNothing().`when`(walletInteractor).executeTransaction(testTransaction)
        doNothing().`when`(router).exit()
        addTransactionPresenter.addTransaction(testTransaction)
        verify(walletInteractor).executeTransaction(testTransaction)
        verify(router).exit()
        verifyNoMoreInteractions(router)
        verifyNoMoreInteractions(walletInteractor)
    }

    @Test
    fun testTypeTransactionChangeIncome() {
        doReturn(listOf<String>()).`when`(walletInteractor).getIncomeCategories()
        doNothing().`when`(addTransactionView).setCategories(listOf())
        addTransactionPresenter.typeTransactionChange(1)
        verify(addTransactionView).setCategories(listOf())
        verify(walletInteractor).getIncomeCategories()
    }

    @Test
    fun testTypeTransactionChangeExpense() {
        doReturn(listOf<String>()).`when`(walletInteractor).getExpenseCategories()
        doNothing().`when`(addTransactionView).setCategories(listOf())
        addTransactionPresenter.typeTransactionChange(0)
        verify(addTransactionView).setCategories(listOf())
        verify(walletInteractor).getExpenseCategories()
    }
}
