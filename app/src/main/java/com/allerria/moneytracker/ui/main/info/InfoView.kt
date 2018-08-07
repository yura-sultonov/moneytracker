package com.allerria.moneytracker.ui.main.info

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoView : MvpView {
    fun setBalanceSum(balanceSum: String)
    fun setMonthExpense(expenseSum: String)
    fun setMonthIncome(incomeSum: String)
}