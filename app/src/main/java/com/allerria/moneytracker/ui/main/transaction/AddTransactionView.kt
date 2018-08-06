package com.allerria.moneytracker.ui.main.transaction

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AddTransactionView : MvpView {
    fun setWallets(wallets: kotlin.collections.List<com.allerria.moneytracker.Wallets>)
}