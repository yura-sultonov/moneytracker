package com.allerria.moneytracker.di

import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.di.modules.ApiModule
import com.allerria.moneytracker.di.modules.AppModule
import com.allerria.moneytracker.di.modules.NavigationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, NavigationModule::class, ActivityBuilder::class, ApiModule::class])
interface AppComponent {

    fun inject(app: MoneyTrackerApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoneyTrackerApp): Builder

        fun build(): AppComponent
    }
}