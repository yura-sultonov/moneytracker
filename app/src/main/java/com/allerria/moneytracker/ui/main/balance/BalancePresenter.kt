package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.model.BalanceManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BalancePresenter(private val balanceManager: BalanceManager): MvpPresenter<BalanceView>() {
    fun showBalance() {
        viewState.showBalance(balanceManager.getBalanceInRUB(), balanceManager.getBalanceInUSD())
    }
}