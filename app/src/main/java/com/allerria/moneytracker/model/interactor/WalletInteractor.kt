package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import javax.inject.Inject


class WalletInteractor @Inject constructor(private val converterInteractor: ConverterInteractor, private val transactionsRepository: TransactionsRepository, private val walletRepository: WalletRepository) {

    fun getWallets(): List<Wallet> {
        return walletRepository.getWallets()
    }

    fun getBalance(uid: String): List<Money> = converterInteractor.convert(Money(Currency.USD, walletRepository.getBalance(uid)!!.value))

    fun setBalance(uid: String, value: Double) {
        walletRepository.setBalance(uid, value)
    }

    fun updateCurrenciesRate() {
        converterInteractor.updateCurrencyRateCache()
    }

    fun executeTransaction(transaction: Transaction) {
        val walletBalance = walletRepository.getBalance(transaction.walletUid)!!.value
        when (transaction.type) {
            TransactionType.EXPENSE -> walletRepository.setBalance(transaction.walletUid, walletBalance - transaction.money.value)
            TransactionType.INCOME -> walletRepository.setBalance(transaction.walletUid, walletBalance + transaction.money.value)
        }
        transactionsRepository.addTransaction(transaction)
    }

    fun executeTransactions(transactions: List<Transaction>) {
        transactions.forEach { executeTransaction(it) }
    }
}