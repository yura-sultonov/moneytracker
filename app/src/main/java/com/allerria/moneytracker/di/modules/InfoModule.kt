package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.model.data.repository.SettingsRepository
import com.allerria.moneytracker.model.data.repository.TransactionsRepository
import com.allerria.moneytracker.model.data.repository.WalletRepository
import com.allerria.moneytracker.model.interactor.ConverterInteractor
import com.allerria.moneytracker.model.interactor.InfoInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class InfoModule {

    @Module
    companion object {
        @Provides
        @Singleton
        fun provideInfoInteractor(
                converterInteractor: ConverterInteractor,
                transactionsRepository: TransactionsRepository,
                walletRepository: WalletRepository,
                settingsRepository: SettingsRepository
        ): InfoInteractor = InfoInteractor(converterInteractor, transactionsRepository, walletRepository, settingsRepository)

    }

}