package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.WalletInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class WalletChartModule {
    @Module
    companion object {
        @Provides
        fun provideWalletInteractor(
                converterInteractor: ConverterInteractor,
                transactionsRepository: TransactionsRepository,
                walletRepository: WalletRepository
        ): WalletInteractor = WalletInteractor(
                converterInteractor,
                transactionsRepository,
                walletRepository
        )

        @Provides
        @Singleton
        fun provideConverterInteractor(
                currencyRateRepository: CurrencyRateRepository
        ): ConverterInteractor = ConverterInteractor(currencyRateRepository)
    }
}