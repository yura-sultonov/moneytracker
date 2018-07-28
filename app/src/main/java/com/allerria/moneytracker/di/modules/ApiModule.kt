package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    val baseUrl = "https://openexchangerates.org/"

    @Provides
    @Singleton
    fun provideCurrencyRateApi(): CurrencyRateApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CurrencyRateApi::class.java)
}