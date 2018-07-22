package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance

class BalanceManager {
    var balance: Balance = Balance(1.0)
    fun getBalanceInRUB() = MoneyConverter.convertToRUB(balance)
    fun getBalanceInUSD() = MoneyConverter.convertToUSD(balance)
}