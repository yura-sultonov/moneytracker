package com.allerria.moneytracker.ui.main.transaction

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AddTransactionPresenter @Inject constructor(walletInteractor: WalletInteractor): MvpPresenter<AddTransactionView>()