package com.allerria.moneytracker.model.data.datasource.local

import com.squareup.sqldelight.ColumnAdapter
import java.util.*

class DateAdapter : ColumnAdapter<Calendar, Long> {
    override fun encode(value: Calendar) = value.timeInMillis
    override fun decode(databaseValue: Long) = Calendar.getInstance().apply {
        timeInMillis = databaseValue
    }
}