package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.repository.CurrencyRateRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.wallet.WalletFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class BalanceModule {

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

    @ContributesAndroidInjector(modules = [WalletModule::class])
    abstract fun bindWalletFragment(): WalletFragment

}