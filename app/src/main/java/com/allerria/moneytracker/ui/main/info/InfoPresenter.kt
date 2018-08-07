package com.allerria.moneytracker.ui.main.info

import com.allerria.moneytracker.model.interactor.InfoInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class InfoPresenter @Inject constructor(private val infoInteractor: InfoInteractor) : MvpPresenter<InfoView>() {

    fun initInfo() {
        viewState.setBalanceSum("${infoInteractor.getSignOfCurrentCurrency()} ${infoInteractor.getSumOfBalances()}")
        viewState.setMonthExpense("- ${infoInteractor.getSignOfCurrentCurrency()} ${infoInteractor.getSumOfMonthExpense()}")
        viewState.setMonthIncome("+ ${infoInteractor.getSignOfCurrentCurrency()} ${infoInteractor.getSumOfMonthIncome()}")
    }

}