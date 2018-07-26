package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.datasource.local.CurrencyRateCache
import com.allerria.moneytracker.model.data.datasource.local.WalletCache
import com.allerria.moneytracker.model.data.datasource.remote.CurrencyRateApi
import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.FinanceManagerInteractor
import com.allerria.moneytracker.model.interactor.MoneyConverterInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FinanceModule {

    @Provides
    @Singleton
    fun provideFinanceManagerInteractor(moneyConverterInteractor: MoneyConverterInteractor, walletRepository: WalletRepository): FinanceManagerInteractor = FinanceManagerInteractor(moneyConverterInteractor, walletRepository)

    @Provides
    @Singleton
    fun provideMoneyConverterInteractor(currencyRateRepository: CurrencyRateRepository): MoneyConverterInteractor = MoneyConverterInteractor(currencyRateRepository)

    @Provides
    @Singleton
    fun provideCurrencyRateRepository(currencyRateApi: CurrencyRateApi, currencyRateCache: CurrencyRateCache): CurrencyRateRepository = CurrencyRateRepository(currencyRateApi, currencyRateCache)

    @Provides
    @Singleton
    fun provideWalletRepository(walletCache: WalletCache): WalletRepository = WalletRepository(walletCache)

    @Provides
    @Singleton
    fun provideCurrencyRateCache(): CurrencyRateCache = CurrencyRateCache()

    @Provides
    @Singleton
    fun provideWalletCache(): WalletCache = WalletCache()

}