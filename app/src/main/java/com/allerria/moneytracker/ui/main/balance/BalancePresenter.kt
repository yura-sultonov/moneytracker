package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Record
import com.allerria.moneytracker.model.FinanceManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.util.*

@InjectViewState
class BalancePresenter(private val financeManager: FinanceManager): MvpPresenter<BalanceView>() {
    fun showBalance() {
        viewState.showBalance(financeManager.getBalance())
    }
    fun makeTransaction() {
        financeManager.executeTransaction(Record("income", Money("RUB", 30.0)))
        financeManager.executeTransaction(Record("consumption", Money("USD", 5.00)))
    }
}