package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.*


class FinanceManager(private val moneyConverter: MoneyConverter) {

    var balance: Balance = Balance(1.0)

    fun getBalance(): List<Money> = moneyConverter.convert(Money(Currency.USD, balance.value))

    fun executeTransaction(record: Record) {
        when (record.type) {
            RecordType.CONSUMPTION -> balance.value -= moneyConverter.convert(record.money, Currency.USD).value
            RecordType.INCOME -> balance.value += moneyConverter.convert(record.money, Currency.USD).value
        }
    }

    fun executeTransactions(records: List<Record>) {
        records.forEach { executeTransaction(it) }
    }
}