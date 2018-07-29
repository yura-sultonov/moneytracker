package com.allerria.moneytracker.ui.main.settings

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(private val walletInteractor: WalletInteractor) : MvpPresenter<SettingsView>() {
    fun wipeData() {
        walletInteractor.wipeData()
    }
}