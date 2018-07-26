package com.allerria.moneytracker.ui.main.settings

import com.allerria.moneytracker.model.FinanceManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class SettingsPresenter(private val financeManager: FinanceManager): MvpPresenter<SettingsView>() {
    fun wipeData() {
        financeManager.balance.value = 0.0
    }
}