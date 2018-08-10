package com.allerria.moneytracker.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import com.allerria.moneytracker.model.data.datasource.local.CurrencyRateCache
import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import com.allerria.moneytracker.model.data.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: MoneyTrackerApp): Context = app

    @Provides
    @Singleton
    fun provideCurrencyRateRepository(
            currencyRateApi: CurrencyRateApi,
            currencyRateCache: CurrencyRateCache
    ): CurrencyRateRepository = CurrencyRateRepository(currencyRateApi, currencyRateCache)

    @Provides
    @Singleton
    fun provideWalletRepository(appDbHelper: AppDbHelper): WalletRepository = WalletRepository(appDbHelper)

    @Provides
    @Singleton
    fun provideTemplateRepository(appDbHelper: AppDbHelper): TemplateRepository = TemplateRepository(appDbHelper)

    @Provides
    @Singleton
    fun provideTransactionsRepository(appDbHelper: AppDbHelper): TransactionsRepository = TransactionsRepository(appDbHelper)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideSettingsRepository(sharedPreferences: SharedPreferences) = SettingsRepository(sharedPreferences)


    @Provides
    @Singleton
    fun provideCurrencyRateCache(): CurrencyRateCache = CurrencyRateCache()

    @Provides
    @Singleton
    fun provideDbHelper(context: Context): AppDbHelper = AppDbHelper(context)

}