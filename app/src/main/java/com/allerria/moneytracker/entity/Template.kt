package com.allerria.moneytracker.entity

import java.util.*

class Template(
        val id: Long,
        val type: TemplateType,
        val enabled: Boolean,
        val transactionTypee: TransactionType,
        val transactionCategory: String,
        val transactionCurrency: Currency,
        val transactionAmount: Double,
        val transactionWalletId: Long,
        val transactionDetails: String,
        val lastRunDate: Date
)