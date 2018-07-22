package com.allerria.moneytracker.di

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.FinanceManager
import com.allerria.moneytracker.model.MoneyConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {
    @Provides
    @Singleton
    fun provideApplication(app: MoneyTrackerApp): Context = app

    @Provides
    @Singleton
    fun provideFinanceManager(moneyConverter: MoneyConverter): FinanceManager = FinanceManager(moneyConverter)

}