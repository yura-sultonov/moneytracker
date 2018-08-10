package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import javax.inject.Inject


open class WalletInteractor @Inject constructor(private val converterInteractor: ConverterInteractor, private val transactionsRepository: TransactionsRepository, private val walletRepository: WalletRepository) {

    fun getWallets(): List<Wallets> {
        return walletRepository.getWallets()
    }

    fun getWallet(id: Long): Wallets = walletRepository.getWalletById(id)

    fun getBalance(id: Long): List<Money> = converterInteractor.convert(Money(Currency.USD, walletRepository.getBalance(id)))

    fun getTransactions(): List<Transactions> = transactionsRepository.getTransactions()

    fun getTransactions(id: Long): List<Transactions> = transactionsRepository.getTransactionsWalletId(id)

    fun setBalance(id: Long, value: Double) {
        walletRepository.setBalance(id, value)
    }

    fun updateCurrenciesRate() {
        converterInteractor.updateCurrencyRateCache()
    }

    fun executeTransaction(transaction: Transaction) {
        var walletBalance = walletRepository.getBalance(transaction.walletId)
        when (transaction.type) {
            TransactionType.EXPENSE -> walletBalance -= transaction.amount
            TransactionType.INCOME -> walletBalance += transaction.amount
        }
        transactionsRepository.addTransaction(transaction)
        setBalance(transaction.walletId, walletBalance)
    }

    fun addWallet(wallet: Wallet) {
        walletRepository.addWallet(wallet)
    }

    fun wipeData() {
        walletRepository.clear()
    }

    fun getIncomeCategories(): List<String> = transactionsRepository.getIncomeCategories()

    fun getExpenseCategories(): List<String> = transactionsRepository.getExpenseCategories()
}