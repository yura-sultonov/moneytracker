package com.allerria.moneytracker.ui.main.template

import com.allerria.moneytracker.Templates
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class TemplatesAdapter : ListDelegationAdapter<MutableList<Templates>>() {

    init {
        items = mutableListOf<Templates>()
        delegatesManager.addDelegate(TemplatesAdapterDelegate())
    }

    fun setData(data: List<Templates>) {
        items = data.toMutableList()
        notifyDataSetChanged()
    }
}