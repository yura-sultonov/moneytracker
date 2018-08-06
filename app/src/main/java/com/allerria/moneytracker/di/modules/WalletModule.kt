package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.wallet.WalletChartDialogFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class WalletModule {
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
    }

    @ContributesAndroidInjector(modules = [WalletChartModule::class])
    abstract fun bindWalletChartDialogFragment(): WalletChartDialogFragment

}