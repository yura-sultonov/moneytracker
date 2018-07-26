package com.allerria.moneytracker.ui.main.settings

import com.allerria.moneytracker.model.interactor.FinanceManagerInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class SettingsPresenter(private val financeManagerInteractor: FinanceManagerInteractor): MvpPresenter<SettingsView>() {
    fun wipeData() {
        financeManagerInteractor.setBalance(0.0)
    }
}