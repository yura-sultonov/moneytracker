package com.allerria.moneytracker.di.modules

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import com.allerria.moneytracker.model.data.datasource.local.CurrencyRateCache
import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
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
    fun provideTransactionsRepository(appDbHelper: AppDbHelper): TransactionsRepository = TransactionsRepository(appDbHelper)

    @Provides
    @Singleton
    fun provideCurrencyRateCache(): CurrencyRateCache = CurrencyRateCache()

    @Provides
    @Singleton
    fun provideDbHelper(context: Context): AppDbHelper = AppDbHelper(context)

}