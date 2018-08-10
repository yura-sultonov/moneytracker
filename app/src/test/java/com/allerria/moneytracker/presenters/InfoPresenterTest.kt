package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.model.interactor.InfoInteractor
import com.allerria.moneytracker.ui.main.info.InfoPresenter
import com.allerria.moneytracker.ui.main.info.InfoView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class InfoPresenterTest {

    private lateinit var infoPresenter: InfoPresenter
    private lateinit var infoInteractor: InfoInteractor
    private lateinit var infoView: InfoView

    @Before
    fun init() {
        infoView = mock(InfoView::class.java)
        infoInteractor = mock(InfoInteractor::class.java)
        infoPresenter = InfoPresenter(infoInteractor)
        infoPresenter.attachView(infoView)
    }

    @Test
    fun testInitView() {
        doReturn("$").`when`(infoInteractor).getSignOfCurrentCurrency()
        doReturn(1.0).`when`(infoInteractor).getSumOfMonthIncome()
        doReturn(2.0).`when`(infoInteractor).getSumOfMonthExpense()
        doReturn(3.0).`when`(infoInteractor).getSumOfBalances()
        doNothing().`when`(infoView).setBalanceSum("$ 3.0")
        doNothing().`when`(infoView).setMonthExpense("- 2.0")
        doNothing().`when`(infoView).setMonthIncome("+ 1.0")
        infoPresenter.initInfo()
        verify(infoInteractor, times(3)).getSignOfCurrentCurrency()
        verify(infoInteractor).getSumOfBalances()
        verify(infoInteractor).getSumOfMonthExpense()
        verify(infoInteractor).getSumOfMonthIncome()
        verifyNoMoreInteractions(infoInteractor)
    }
}