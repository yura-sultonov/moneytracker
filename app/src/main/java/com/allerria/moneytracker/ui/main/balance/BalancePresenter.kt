package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class BalancePresenter @Inject constructor(private val walletInteractor: WalletInteractor) : MvpPresenter<BalanceView>() {

    fun showBalance() {
        if (walletInteractor.getWallets().isEmpty()) {
            viewState.refreshWallets()
        }
        walletInteractor.updateCurrenciesRate()
        viewState.showBalance(walletInteractor.getWallets())
    }

    fun updateCurrenciesRate() {
        walletInteractor.updateCurrenciesRate()
        showBalance()
    }
}