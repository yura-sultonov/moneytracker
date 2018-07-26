package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.server.CurrencyRateApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule(private val baseUrl: String) {
    @Provides
    @Singleton
    fun provideCurrencyRateApi(): CurrencyRateApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
            .create(CurrencyRateApi::class.java)
}