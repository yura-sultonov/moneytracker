package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.wallet.WalletPresenter
import com.allerria.moneytracker.ui.main.wallet.WalletView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class WalletPresenterTest {

    private lateinit var walletInteractor: WalletInteractor
    private lateinit var walletPresenter: WalletPresenter
    private lateinit var walletView: WalletView

    private val testWallet = Wallets.Impl(1, "Test", WalletType.CASH, Currency.RUB, 123.0)

    @Before
    fun init() {
        walletView = mock(WalletView::class.java)
        walletInteractor = mock(WalletInteractor::class.java)

        walletPresenter = WalletPresenter(walletInteractor)
        walletPresenter.attachView(walletView)
    }

    @Test
    fun testInitView() {
        doNothing().`when`(walletView).loadWallet(testWallet, listOf())
        doReturn(testWallet).`when`(walletInteractor).getWallet(testWallet.id)
        doReturn(listOf<Transactions>()).`when`(walletInteractor).getTransactions(testWallet.id)
        walletPresenter.initView(testWallet.id)
        verify(walletView).loadWallet(testWallet, listOf())
        verify(walletInteractor).getWallet(testWallet.id)
        verify(walletInteractor).getTransactions(testWallet.id)
        verifyNoMoreInteractions(walletInteractor)
        verifyNoMoreInteractions(walletView)
    }
}