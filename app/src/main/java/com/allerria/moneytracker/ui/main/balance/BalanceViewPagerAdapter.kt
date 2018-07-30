package com.allerria.moneytracker.ui.main.balance

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.ui.main.wallet.WalletFragment
import timber.log.Timber

class BalanceViewPagerAdapter(private val fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private var wallets = mutableListOf<Wallet>()

    override fun getItem(position: Int): Fragment = WalletFragment.newInstance(wallets[position].uid)

    override fun getCount(): Int = wallets.size

    fun setData(wallets: List<Wallet>) {
        this.wallets.clear()
        this.wallets.addAll(wallets)
        notifyDataSetChanged()
        Timber.d(this.wallets.toString())
    }
}