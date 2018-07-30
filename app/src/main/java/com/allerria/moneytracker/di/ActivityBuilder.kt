package com.allerria.moneytracker.di

import com.allerria.moneytracker.di.modules.MainModule
import com.allerria.moneytracker.ui.main.MainActivity
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity (): MainActivity
    
}