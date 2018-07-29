package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import timber.log.Timber
import javax.inject.Inject


class WalletInteractor @Inject constructor(private val converterInteractor: ConverterInteractor, private val transactionsRepository: TransactionsRepository, private val walletRepository: WalletRepository) {

    fun getWallets(): List<Wallet> {
        return walletRepository.getWallets()
    }

    fun getWallet(uid: String): Wallet = walletRepository.getWallet(uid) ?: Wallet("fake", WalletType.CASH, 0.0, "fake", Currency.USD)

    fun getBalance(uid: String): List<Money> = converterInteractor.convert(Money(Currency.USD, walletRepository.getBalance(uid).value))

    fun getTransactions(): List<Transaction> = transactionsRepository.getTransactions()

    fun getTransactions(uid: String): List<Transaction> = transactionsRepository.getTransactions(uid)

    fun setBalance(uid: String, value: Double) {
        walletRepository.setBalance(uid, value)
    }

    fun updateCurrenciesRate() {
        converterInteractor.updateCurrencyRateCache()
    }

    fun executeTransaction(transaction: Transaction) {
        var walletBalance = walletRepository.getBalance(transaction.walletUid).value
        Timber.d(walletBalance.toString())
        when (transaction.type) {
            TransactionType.EXPENSE -> walletBalance -= transaction.money.value
            TransactionType.INCOME -> walletBalance += transaction.money.value
        }
        transactionsRepository.addTransaction(transaction)
        Timber.d(walletBalance.toString())
        setBalance(transaction.walletUid, walletBalance)
        Timber.d(getWallets().toString())
    }

    fun executeTransactions(transactions: List<Transaction>) {
        transactions.forEach { executeTransaction(it) }
    }

    fun addWallet(wallet: Wallet) {
        walletRepository.addWallet(wallet)
    }

    fun wipeData() {
        walletRepository.clear()
        transactionsRepository.clear()
    }
}