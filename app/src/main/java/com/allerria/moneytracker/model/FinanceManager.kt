package com.allerria.moneytracker.model

import com.allerria.moneytracker.entity.Balance
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.entity.Transaction


class FinanceManager(private val moneyConverter: MoneyConverter) {

    var balance: Balance = Balance(1.0)

    fun getBalance(): List<Money> = moneyConverter.convert(Money("USD", balance.money))

    fun executeTransaction(transaction: Transaction) {
        balance.money -= moneyConverter.convert(transaction.money, "USD").value
    }
}