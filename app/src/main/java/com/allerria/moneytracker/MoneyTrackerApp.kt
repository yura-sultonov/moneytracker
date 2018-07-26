package com.allerria.moneytracker

import android.app.Activity
import android.app.Application
import com.allerria.moneytracker.di.AppComponent
import com.allerria.moneytracker.di.DaggerAppComponent
import com.allerria.moneytracker.di.modules.ApiModule
import com.allerria.moneytracker.di.modules.AppModule
import com.allerria.moneytracker.di.modules.FinanceModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import timber.log.Timber


class MoneyTrackerApp : Application() {

    companion object {
        lateinit var INSTANCE: MoneyTrackerApp
        lateinit var component: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
       component = buildComponent()

        INSTANCE = this
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule("https://openexchangerates.org/"))
                .financeModule(FinanceModule())
                .build()
    }

}