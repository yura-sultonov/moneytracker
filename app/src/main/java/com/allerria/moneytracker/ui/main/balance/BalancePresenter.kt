package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Record
import com.allerria.moneytracker.entity.RecordType
import com.allerria.moneytracker.model.FinanceManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class BalancePresenter(private val financeManager: FinanceManager): MvpPresenter<BalanceView>() {

    fun showBalance() {
        viewState.showBalance(financeManager.getBalance())
    }
    fun makeTransaction() {
        financeManager.executeTransaction(Record(RecordType.INCOME, Money(Currency.RUB, 30.0)))
        financeManager.executeTransaction(Record(RecordType.CONSUMPTION, Money(Currency.USD, 5.00)))

    }
}