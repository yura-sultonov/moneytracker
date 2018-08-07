package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.data.repository.SettingsRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import javax.inject.Inject

class InfoInteractor @Inject constructor(private val converterInteractor: ConverterInteractor, private val transactionsRepository: TransactionsRepository, private val walletRepository: WalletRepository, private val settingsRepository: SettingsRepository) {
    fun getSumOfBalances(): Double {
        return walletRepository.getWallets().sumByDouble { converterInteractor.convert(Money(it.currency, it.balance), settingsRepository.getCurrentCurrency()).value }
    }

    fun getSignOfCurrentCurrency(): String {
        return settingsRepository.getCurrentCurrency().sign
    }

    fun getSumOfMonthExpense(): Double {
        return transactionsRepository.getTransactionsByType(TransactionType.EXPENSE).sumByDouble { converterInteractor.convert(Money(it.currency, it.amount), settingsRepository.getCurrentCurrency()).value }
    }

    fun getSumOfMonthIncome(): Double {
        return transactionsRepository.getTransactionsByType(TransactionType.INCOME).sumByDouble { converterInteractor.convert(Money(it.currency, it.amount), settingsRepository.getCurrentCurrency()).value }
    }

}
