package com.allerria.moneytracker.entity

import java.util.*

data class Transaction(
        val uid: String,
        val type: TransactionType,
        val money: Money,
        val walletUid: String,
        val details: String,
        val date: Date
)