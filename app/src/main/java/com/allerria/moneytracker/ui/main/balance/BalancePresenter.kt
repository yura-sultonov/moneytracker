package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Transaction
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
        financeManager.executeTransaction(Transaction(Date(), Money("USD", 2.00)))
    }
}