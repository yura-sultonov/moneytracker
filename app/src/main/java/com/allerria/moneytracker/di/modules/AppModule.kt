package com.allerria.moneytracker.di.modules

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.FinanceManager
import com.allerria.moneytracker.model.MoneyConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideFinanceManager(moneyConverter: MoneyConverter): FinanceManager = FinanceManager(moneyConverter)
}