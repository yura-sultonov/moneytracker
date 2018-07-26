package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.CurrencyRateManager
import com.allerria.moneytracker.model.MoneyConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FinanceModule {
    @Provides
    @Singleton
    fun provideCurrencyRateManager(): CurrencyRateManager = CurrencyRateManager()

    @Provides
    @Singleton
    fun provideMoneyConverter(currencyRateManager: CurrencyRateManager): MoneyConverter = MoneyConverter(currencyRateManager)
}