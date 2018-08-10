package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.wallet.WalletChartPresenter
import com.allerria.moneytracker.ui.main.wallet.WalletChartView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class WalletChartPresenterTest {

    private lateinit var walletChartPresenter: WalletChartPresenter
    private lateinit var walletChartView: WalletChartView
    private lateinit var walletInteractor: WalletInteractor

    @Before
    fun init() {
        walletChartView = mock(WalletChartView::class.java)
        walletInteractor = mock(WalletInteractor::class.java)

        walletChartPresenter = WalletChartPresenter(walletInteractor)
        walletChartPresenter.attachView(walletChartView)
    }

    @Test
    fun testBuildChart() {
        doNothing().`when`(walletChartView).buildChart(listOf())
        doReturn(listOf<Transactions>()).`when`(walletInteractor).getTransactions(0)
        walletChartPresenter.buildChart(0)
        verify(walletInteractor).getTransactions(0)
        verify(walletChartView).buildChart(listOf())
        verifyNoMoreInteractions(walletInteractor)
        verifyNoMoreInteractions(walletChartView)
    }

}