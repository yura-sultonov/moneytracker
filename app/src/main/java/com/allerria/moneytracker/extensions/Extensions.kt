package com.allerria.moneytracker.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.entity.Currency
import java.text.NumberFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Double.formatMoney(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    return format.format(this).dropLast(1)
}