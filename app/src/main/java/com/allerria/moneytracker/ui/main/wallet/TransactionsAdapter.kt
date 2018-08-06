package com.allerria.moneytracker.ui.main.wallet

import com.allerria.moneytracker.Transactions
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class TransactionsAdapter : ListDelegationAdapter<MutableList<Transactions>>() {

    init {
        items = mutableListOf<Transactions>()
        delegatesManager.addDelegate(TransactionsAdapterDelegate())
    }

    fun setData(data: List<Transactions>) {
        items = data.toMutableList()
        notifyDataSetChanged()
    }
}