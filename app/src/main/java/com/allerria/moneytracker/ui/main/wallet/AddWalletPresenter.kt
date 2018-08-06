package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AddWalletPresenter @Inject constructor(private val walletInteractor: WalletInteractor, private val router: Router) : MvpPresenter<AddWalletView>() {
    fun addWallet(wallet: Wallet) {
        walletInteractor.addWallet(wallet)
        router.backTo(Screens.BALANCE_SCREEN)
    }
}