package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.Wallet
import com.arellomobile.mvp.MvpView

interface WalletView: MvpView {
    fun loadWallet(wallet: Wallet, transactions: List<Transaction>)
}