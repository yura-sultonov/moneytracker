package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import java.util.*
import javax.inject.Inject

@InjectViewState
class BalancePresenter @Inject constructor(private val walletInteractor: WalletInteractor): MvpPresenter<BalanceView>() {

    fun showBalance() {
        viewState.showBalance(walletInteractor.getBalance(walletInteractor.getWallets()[0].uid))
    }
    fun addTransaction() {
        //walletInteractor.executeTransaction(Transaction(UUID.randomUUID().toString(), TransactionType.INCOME, Money(Currency.RUB, 30.0), walletInteractor.getWallets()[0].uid))
        //walletInteractor.executeTransaction(Transaction(UUID.randomUUID().toString(), TransactionType.EXPENSE, Money(Currency.USD, 1.00), walletInteractor.getWallets()[0].uid))
        showBalance()
    }
    fun updateCurrenciesRate() {
        walletInteractor.updateCurrenciesRate()
        showBalance()
    }
}