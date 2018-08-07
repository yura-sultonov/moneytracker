package com.allerria.moneytracker.ui.main.balance

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.ui.main.wallet.WalletFragment
import timber.log.Timber

class BalanceViewPagerAdapter(private val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var wallets: List<Wallets> = mutableListOf<Wallets>()

    override fun getItem(position: Int): Fragment = WalletFragment.newInstance(wallets[position].id)

    override fun getCount(): Int = wallets.size

    fun setData(wallets: List<Wallets>) {
        this.wallets = wallets
        notifyDataSetChanged()
        Timber.d(this.wallets.toString())
    }
}