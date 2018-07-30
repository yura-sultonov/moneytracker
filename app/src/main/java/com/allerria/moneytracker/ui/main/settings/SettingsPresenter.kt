package com.allerria.moneytracker.ui.main.settings

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class SettingsPresenter @Inject constructor(private val walletInteractor: WalletInteractor, private val router: Router) : MvpPresenter<SettingsView>() {
    fun wipeData() {
        walletInteractor.wipeData()
        router.exit()
    }
}