package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.balance.BalancePresenter
import com.allerria.moneytracker.ui.main.balance.BalanceView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class BalancePresenterTest {

    private lateinit var balancePresenter: BalancePresenter
    private lateinit var walletInteractor: WalletInteractor
    private lateinit var balanceView: BalanceView

    @Before
    fun init() {
        walletInteractor = mock(WalletInteractor::class.java)
        balancePresenter = BalancePresenter(walletInteractor)
        balanceView = mock(BalanceView::class.java)
        balancePresenter.attachView(balanceView)
    }

    @Test
    fun testShowBalance() {
        doReturn(arrayListOf<Wallets>()).`when`(walletInteractor).getWallets()
        doNothing().`when`(walletInteractor).updateCurrenciesRate()
        doNothing().`when`(balanceView).showBalance(arrayListOf<Wallets>())
        doNothing().`when`(balanceView).refreshWallets()
        balancePresenter.showBalance()
        verify(walletInteractor, times(2)).getWallets()
        verify(walletInteractor).updateCurrenciesRate()
        verify(balanceView).refreshWallets()
        verify(balanceView).showBalance(arrayListOf())
        verifyNoMoreInteractions(balanceView)
        verifyNoMoreInteractions(walletInteractor)
    }

    @Test
    fun testUpdateCurrenciesRate() {
        doNothing().`when`(walletInteractor).updateCurrenciesRate()
        doReturn(arrayListOf<Wallets>()).`when`(walletInteractor).getWallets()
        doNothing().`when`(walletInteractor).updateCurrenciesRate()
        doNothing().`when`(balanceView).showBalance(arrayListOf<Wallets>())
        doNothing().`when`(balanceView).refreshWallets()
        balancePresenter.updateCurrenciesRate()
        verify(walletInteractor, times(2)).getWallets()
        verify(walletInteractor, times(2)).updateCurrenciesRate()
        verify(balanceView).refreshWallets()
        verify(balanceView).showBalance(arrayListOf())
        verifyNoMoreInteractions(balanceView)
        verifyNoMoreInteractions(walletInteractor)
    }
}