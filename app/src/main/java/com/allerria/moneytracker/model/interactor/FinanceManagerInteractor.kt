package com.allerria.moneytracker.model.interactor

import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.model.data.repository.WalletRepository
import javax.inject.Inject


class FinanceManagerInteractor @Inject constructor(private val moneyConverterInteractor: MoneyConverterInteractor, private val walletRepository: WalletRepository) {

    fun getBalance(): List<Money> {
        return moneyConverterInteractor.convert(Money(Currency.USD, walletRepository.getBalance()))
    }

    fun setBalance(value: Double) {
        walletRepository.setBalance(value)
    }

    fun updateCurrenciesRate() {
        moneyConverterInteractor.updateCurrencyRateCache()
    }

    fun executeTransaction(record: Record) {
        when (record.type) {
            RecordType.CONSUMPTION -> walletRepository.setBalance(walletRepository.getBalance() - moneyConverterInteractor.convert(record.money, Currency.USD).value)
            RecordType.INCOME -> walletRepository.setBalance(walletRepository.getBalance() + moneyConverterInteractor.convert(record.money, Currency.USD).value)
        }
    }

    fun executeTransactions(records: List<Record>) {
        records.forEach { executeTransaction(it) }
    }
}