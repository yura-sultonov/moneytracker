package com.allerria.moneytracker.model.data.datasource.local

import com.allerria.moneytracker.entity.Transaction

class TransactionsCache {
    val transactions: MutableList<Transaction> = arrayListOf()
}