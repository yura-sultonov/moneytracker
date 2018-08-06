package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import java.util.*
import javax.inject.Inject

class TransactionsRepository @Inject constructor(private val db: AppDbHelper) {

    fun getTransactions() = db.wrapper.transactionQueries.selectAll().executeAsList()

    fun getTransactionsByType(transactionType: TransactionType) = db.wrapper.transactionQueries.selectAllByTransactionType(transactionType).executeAsList()

    fun addTransaction(transaction: Transaction) {
        db.wrapper.transactionQueries.insertTransaction(transaction.type, transaction.category, transaction.currency, transaction.amount, transaction.walletId, transaction.details, GregorianCalendar())
    }

    fun getTransactionsWalletId(id: Long) = db.wrapper.transactionQueries.selectAllByWalletId(id).executeAsList()

    fun getTransactionsByCategory(transactionType: TransactionType) = db.wrapper.transactionQueries.selectAllByCategory(transactionType.toString())

    fun getTransactionsByCurrency(currency: Currency) = db.wrapper.transactionQueries.selectAllByCurrency(currency)

    fun getTransactionsFromDate(date: Calendar) = db.wrapper.transactionQueries.selectAllFromDate(date)

    fun getTransactionInInterval(from: Calendar, to: Calendar) = db.wrapper.transactionQueries.selectAllInInterval(from, to)

    fun deleteAllByWalletId(id: Long) {
        db.wrapper.transactionQueries.deleteAllByWalletId(id)
    }
}