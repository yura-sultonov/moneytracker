package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AddWalletModule {
    @Module
    companion object {
        @Provides
        @Singleton
        fun provideConverterInteractor(
                currencyRateRepository: CurrencyRateRepository
        ): ConverterInteractor = ConverterInteractor(currencyRateRepository)
    }
}