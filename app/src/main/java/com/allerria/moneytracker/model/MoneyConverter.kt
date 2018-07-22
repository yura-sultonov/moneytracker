package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance

class MoneyConverter {
    companion object {
        fun convertToUSD(balance: Balance) = balance.money
        fun convertToRUB(balance: Balance) = balance.money * 60
    }
}