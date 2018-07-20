package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance

class BalanceManager {

    companion object {
        var balance: Balance = Balance(0.0)
        fun getBalanceInRubles() = MoneyConverter.convertToRubles(balance)
        fun getBalanceInDollars() =  MoneyConverter.convertToDollars(balance)
    }
}