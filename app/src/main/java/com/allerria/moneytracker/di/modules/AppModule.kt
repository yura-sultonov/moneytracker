package com.allerria.moneytracker.di.modules

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.data.datasource.local.CurrencyRateCache
import com.allerria.moneytracker.model.data.datasource.local.TransactionsCache
import com.allerria.moneytracker.model.data.datasource.local.WalletCache
import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.ui.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
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
    fun provideWalletRepository(walletCache: WalletCache): WalletRepository = WalletRepository(walletCache)

    @Provides
    @Singleton
    fun provideTransactionsRepository(transactionsCache: TransactionsCache): TransactionsRepository = TransactionsRepository(transactionsCache)

    @Provides
    @Singleton
    fun provideCurrencyRateCache(): CurrencyRateCache = CurrencyRateCache()

    @Provides
    @Singleton
    fun provideWalletCache(context: Context): WalletCache = WalletCache(context)

    @Provides
    @Singleton
    fun provideTransactionCache(): TransactionsCache = TransactionsCache()

    @Provides
    @Singleton
    fun provideMoneyConverterInteractor(
            currencyRateRepository: CurrencyRateRepository
    ): ConverterInteractor = ConverterInteractor(currencyRateRepository)

}