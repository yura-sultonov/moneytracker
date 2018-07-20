package com.allerria.moneytracker.ui.main

import com.allerria.moneytracker.model.BalanceManager

class MainPresenter(val view: MainView) {
    fun showBalance() {
        BalanceManager.balance.money += 1.0
        view.showBalance(BalanceManager.getBalanceInRubles(), BalanceManager.getBalanceInDollars())
    }
}