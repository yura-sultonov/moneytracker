package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class WalletPresenter @Inject constructor(private val walletInteractor: WalletInteractor) : MvpPresenter<WalletView>() {
    fun initView(id: Long) {
        Timber.d(id.toString())
        Timber.d(walletInteractor.getWallets().toString())
        viewState.loadWallet(walletInteractor.getWallet(id), walletInteractor.getTransactions(id))
    }
}