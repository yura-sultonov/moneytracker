package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.info.InfoFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import com.allerria.moneytracker.ui.main.transaction.AddTransactionFragment
import com.allerria.moneytracker.ui.main.wallet.AddWalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector(modules = [BalanceModule::class])
    abstract fun bindBalanceFragment(): BalanceFragment

    @ContributesAndroidInjector(modules = [SettingsModule::class])
    abstract fun bindSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector(modules = [AboutModule::class])
    abstract fun bindAboutFragment(): AboutFragment

    @ContributesAndroidInjector(modules = [AddWalletModule::class])
    abstract fun bindAddWalletFragment(): AddWalletFragment

    @ContributesAndroidInjector(modules = [AddTransactionModule::class])
    abstract fun bindAddTransactionFragment(): AddTransactionFragment

    @ContributesAndroidInjector(modules = [InfoModule::class])
    abstract fun bindInfoFragment(): InfoFragment
}