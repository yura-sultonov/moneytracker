package com.allerria.moneytracker.di

import com.allerria.moneytracker.ui.about.AboutActivity
import com.allerria.moneytracker.ui.main.MainActivity
import com.allerria.moneytracker.ui.settings.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity (): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSettignsActivity (): SettingsActivity

    @ContributesAndroidInjector
    abstract fun bindAboutActivity (): AboutActivity
}