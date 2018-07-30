package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.entity.Transaction
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface WalletChartView: MvpView {
    fun buildChart(transactions: List<Transaction>)
}