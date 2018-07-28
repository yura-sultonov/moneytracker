package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.model.data.datasource.local.TransactionsCache
import javax.inject.Inject

class TransactionsRepository @Inject constructor(private val transactionsCache: TransactionsCache) {
    fun getTransactions() = transactionsCache.transactions

    fun addTransaction(transaction: Transaction) {
        transactionsCache.transactions.add(transaction)
    }
}