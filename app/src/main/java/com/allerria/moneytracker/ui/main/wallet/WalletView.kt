package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.arellomobile.mvp.MvpView

interface WalletView : MvpView {
    fun loadWallet(wallet: Wallets, transactions: List<Transactions>)
}