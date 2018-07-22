package com.allerria.moneytracker

import android.app.Activity
import android.app.Application
import com.allerria.moneytracker.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoneyTrackerApp : Application(), HasActivityInjector {

    companion object {
        @JvmStatic lateinit var INSTANCE : MoneyTrackerApp
    }


    @Inject
    lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}