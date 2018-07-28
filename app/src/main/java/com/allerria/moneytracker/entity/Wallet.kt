package com.allerria.moneytracker.entity

import java.util.*

//value is usd because dollar is stable currency
data class Wallet(val uid: String, val type: WalletType, var value: Double, val name: String, var currency: Currency)

