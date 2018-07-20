package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance

class MoneyConverter {
    companion object {
        fun convertToDollars(balance: Balance) = balance.money
        fun convertToRubles(balance: Balance) = balance.money * 60
    }
}