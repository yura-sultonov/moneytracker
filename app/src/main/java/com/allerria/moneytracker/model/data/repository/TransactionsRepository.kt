package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class TransactionsRepository @Inject constructor(private val db: AppDbHelper) {

    fun getTransactions() = db.wrapper.transactionQueries.selectAll().executeAsList()

    fun getTransactionsByType(transactionType: TransactionType) = db.wrapper.transactionQueries.selectAllByTransactionType(transactionType).executeAsList()

    fun addTransaction(transaction: Transaction) {
        db.wrapper.transactionQueries.insertTransaction(transaction.type, transaction.category, transaction.currency, transaction.amount, transaction.walletId, transaction.details, GregorianCalendar())
        Timber.i("Transaction added")
    }

    fun getTransactions(id: Long) = db.wrapper.transactionQueries.selectAllByWalletId(id).executeAsList()
}