package com.allerria.moneytracker.entity

import java.util.*

class Template(
        val type: TemplateType,
        val enabled: Boolean,
        val transactionTypee: TransactionType,
        val transactionCategory: String,
        val transactionCurrency: Currency,
        val transactionAmount: Double,
        val transactionWalletId: Long,
        val lastRunDate: Date
)