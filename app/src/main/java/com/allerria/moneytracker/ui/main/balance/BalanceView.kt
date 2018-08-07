package com.allerria.moneytracker.ui.main.balance

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BalanceView : MvpView {
    fun showBalance(wallets: kotlin.collections.List<com.allerria.moneytracker.Wallets>)
    fun refreshWallets()
}