package com.allerria.moneytracker.ui.main.balance

import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.Wallet
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BalanceView: MvpView {
    fun showBalance(wallets: List<Wallet>)
}