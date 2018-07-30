package com.allerria.moneytracker.entity.remote

import com.google.gson.annotations.SerializedName

data class CurrencyRateRemote(
        @SerializedName("disclaimer") val disclaimer: String,
        @SerializedName("license") val license: String,
        @SerializedName("timestamp") val timestamp: Int,
        @SerializedName("base") val base: String,
        @SerializedName("rates") val rates: Rates
)