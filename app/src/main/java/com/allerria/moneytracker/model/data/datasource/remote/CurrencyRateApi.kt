package com.allerria.moneytracker.model.data.datasource.remote

import com.allerria.moneytracker.entity.remote.CurrencyRateRemote
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyRateApi {
    @GET("api/latest.json?app_id=76336b9c81d34955ab27e23f482c0636")
    fun getCurrencyRate(): Single<CurrencyRateRemote>
}