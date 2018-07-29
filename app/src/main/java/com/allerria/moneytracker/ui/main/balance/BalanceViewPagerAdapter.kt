package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.entity.WalletType
import kotlinx.android.synthetic.main.item_wallet.view.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BalanceViewPagerAdapter @Inject constructor(private val router: Router) : PagerAdapter() {

    private var wallets = mutableListOf<Wallet>()

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = wallets.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater = container.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView = mLayoutInflater.inflate(R.layout.item_wallet, container, false)

        with(itemView) {
            val last = count - 1
            if (position != last) {
                balance_text_view.text = wallets[position].value.toString()
                type_text_view.text = wallets[position].currency.sign
            } else {
                add_wallet_image_button.visibility = View.VISIBLE
                balance_text_view.visibility = View.GONE
                type_text_view.visibility = View.GONE
                add_wallet_image_button.setOnClickListener { router.navigateTo(Screens.ADD_WALLET_SCREEN) }
            }

        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    fun setData(wallets: List<Wallet>) {
        this.wallets.clear()
        this.wallets.addAll(wallets)
        this.wallets.add(Wallet("addwallet", WalletType.CARD, 0.0, "addwallet", Currency.USD))
        notifyDataSetChanged()
    }
}