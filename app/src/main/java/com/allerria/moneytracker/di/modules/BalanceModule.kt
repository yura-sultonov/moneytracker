package com.allerria.moneytracker.di.modules

import com.allerria.moneytracker.ui.main.wallet.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BalanceModule {

    @ContributesAndroidInjector(modules = [WalletModule::class])
    abstract fun bindWalletFragment(): WalletFragment

}