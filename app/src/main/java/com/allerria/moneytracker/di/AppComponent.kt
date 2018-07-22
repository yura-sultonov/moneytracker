package com.allerria.moneytracker.di

import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.ui.settings.SettingsActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class, ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoneyTrackerApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoneyTrackerApp)
}