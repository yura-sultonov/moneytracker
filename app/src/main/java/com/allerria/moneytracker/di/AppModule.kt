package com.allerria.moneytracker.di

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.model.BalanceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule()
{
    @Provides
    @Singleton
    fun provideApplication(app : MoneyTrackerApp): Context = app

    @Provides
    @Singleton
    fun provideBalanceManager(): BalanceManager = BalanceManager()
}