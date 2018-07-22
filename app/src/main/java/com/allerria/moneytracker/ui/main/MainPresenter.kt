package com.allerria.moneytracker.ui.main

import com.allerria.moneytracker.model.BalanceManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter(private val balanceManager: BalanceManager): MvpPresenter<MainView>() {
    fun showBalance() {
        viewState.showBalance(balanceManager.getBalanceInRUB(), balanceManager.getBalanceInUSD())
    }
}