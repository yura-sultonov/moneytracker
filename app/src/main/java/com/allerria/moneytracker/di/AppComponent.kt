package com.allerria.moneytracker.di

import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class, FinanceModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoneyTrackerApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoneyTrackerApp)
    fun inject(balanceFragment: BalanceFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(aboutFragment: AboutFragment)

}