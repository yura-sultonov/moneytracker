package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Record


class FinanceManager(private val moneyConverter: MoneyConverter) {

    var balance: Balance = Balance(1.0)

    fun getBalance(): List<Money> = moneyConverter.convert(Money("USD", balance.money))

    fun executeTransaction(record: Record) {
        when (record.type) {
            "consumption" -> balance.money -= moneyConverter.convert(record.money, "USD").value
            "income" -> balance.money += moneyConverter.convert(record.money, "USD").value
        }
    }

    fun executeTransactions(records: List<Record>) {
        records.forEach { executeTransaction(it) }
    }
}