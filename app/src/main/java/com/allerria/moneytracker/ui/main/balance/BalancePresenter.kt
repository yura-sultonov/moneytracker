package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Record
import com.allerria.moneytracker.entity.RecordType
import com.allerria.moneytracker.model.interactor.FinanceManagerInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BalancePresenter(private val financeManagerInteractor: FinanceManagerInteractor): MvpPresenter<BalanceView>() {

    fun showBalance() {
        viewState.showBalance(financeManagerInteractor.getBalance())
    }
    fun makeTransaction() {
        financeManagerInteractor.executeTransaction(Record(RecordType.INCOME, Money(Currency.RUB, 30.0)))
        financeManagerInteractor.executeTransaction(Record(RecordType.CONSUMPTION, Money(Currency.USD, 5.00)))
    }
    fun updateCurrenciesRate() {
        financeManagerInteractor.updateCurrenciesRate()
    }
}