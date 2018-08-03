package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import timber.log.Timber
import javax.inject.Inject

class WalletRepository @Inject constructor(private val db: AppDbHelper) {

    fun getBalance(id: Long) = db.wrapper.walletQueries.selectWalletById(id).executeAsOne().value

    fun setBalance(id: Long, value: Double) {
//
//        walletCache.wallets.find { it.id == id }!!.value = value
    }

    fun getWallet(id: Long) = db.wrapper.walletQueries.selectWalletById(id).executeAsOne()

    fun getWallets() = db.wrapper.walletQueries.selectAll().executeAsList()

    fun addWallet(wallet: Wallet) {
        db.wrapper.walletQueries.insertWallet(wallet.name, wallet.type, wallet.currency)
        Timber.i("Wallet added")
    }

    fun clear() {
        // db
    }

    fun deleteWallet(id: Long) {
        db.wrapper.walletQueries.deleteWallet(id)
    }
}