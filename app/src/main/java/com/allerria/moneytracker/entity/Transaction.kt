package com.allerria.moneytracker.entity

import java.util.*

class Transaction(
        val id: Long,
        val type: TransactionType,
        val category: String,
        val currency: Currency,
        val amount: Double,
        val walletId: Long,
        val details: String,
        val date: Date
)