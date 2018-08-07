package com.allerria.moneytracker.entity

//value is usd because dollar is stable currency
data class Wallet(val id: Long, val name: String, val type: WalletType, var value: Double, var currency: Currency)

