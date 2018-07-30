package com.allerria.moneytracker.ui.main.wallet

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class TransactionsAdapter: ListDelegationAdapter<MutableList<Any>>() {

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(TransactionsAdapterDelegate())
    }

    fun setData(data: List<Any>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}