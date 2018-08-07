package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class WalletChartPresenter @Inject constructor(private val walletInteractor: WalletInteractor) : MvpPresenter<WalletChartView>() {
    fun buildChart(walletId: Long) {
        Timber.d(walletInteractor.getTransactions(walletId).toString())
        viewState.buildChart(walletInteractor.getTransactions(walletId))
    }
}