package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.R
import com.allerria.moneytracker.entity.Money
import kotlinx.android.synthetic.main.item_balance.view.*

class BalanceViewPagerAdapter: PagerAdapter() {

    private var balances = mutableListOf<Money>()

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = balances.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayoutInflater = container.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemView = mLayoutInflater.inflate(R.layout.item_balance, container, false)

        with(itemView) {
            balance_text_view.text = balances[position].value.toString()
            type_text_view.text = balances[position].currency.sign
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeAllViews()
    }

    fun setData(balances: List<Money>) {
        this.balances.clear()
        this.balances.addAll(balances)
        notifyDataSetChanged()
    }
}