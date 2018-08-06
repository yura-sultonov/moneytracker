package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import javax.inject.Inject

class WalletRepository @Inject constructor(private val db: AppDbHelper) {

    fun getBalance(id: Long) = db.wrapper.walletQueries.selectWalletById(id).executeAsOne().balance

    fun setBalance(id: Long, value: Double) {
        db.wrapper.walletQueries.updateValue(value, id)
    }

    fun getWalletById(id: Long) = db.wrapper.walletQueries.selectWalletById(id).executeAsOne()

    fun getWallets() = db.wrapper.walletQueries.selectAll().executeAsList()

    fun addWallet(wallet: Wallet) {
        db.wrapper.walletQueries.insertWallet(wallet.name, wallet.type, wallet.currency, wallet.value)
    }

    fun clear() {
        db.wrapper.transactionQueries.deleteAll()
        db.wrapper.walletQueries.deleteAllWallet()
    }

    fun deleteWallet(id: Long) {
        db.wrapper.walletQueries.deleteWalletById(id)
    }
}