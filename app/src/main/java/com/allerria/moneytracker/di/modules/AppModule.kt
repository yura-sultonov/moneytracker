package com.allerria.moneytracker.di.modules

import android.content.Context
import com.allerria.moneytracker.model.interactor.FinanceManagerInteractor
import com.allerria.moneytracker.model.interactor.MoneyConverterInteractor
import com.allerria.moneytracker.model.data.repository.WalletRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context
}