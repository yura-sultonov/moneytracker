package com.allerria.moneytracker.di

import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.di.modules.ApiModule
import com.allerria.moneytracker.di.modules.AppModule
import com.allerria.moneytracker.di.modules.FinanceModule
import com.allerria.moneytracker.di.modules.NavigationModule
import com.allerria.moneytracker.ui.main.MainActivity
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, FinanceModule::class, ApiModule::class, NavigationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(balanceFragment: BalanceFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(aboutFragment: AboutFragment)
}