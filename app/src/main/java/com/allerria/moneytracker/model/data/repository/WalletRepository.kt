package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Balance
import com.allerria.moneytracker.model.data.datasource.local.WalletCache
import javax.inject.Inject

class WalletRepository @Inject constructor(private val walletCache: WalletCache) {

    fun getBalance() = walletCache.balance.value

    fun setBalance(value: Double) {
        walletCache.balance.value = value
    }
}