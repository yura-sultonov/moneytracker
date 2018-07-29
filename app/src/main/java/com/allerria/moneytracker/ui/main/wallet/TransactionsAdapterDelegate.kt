package com.allerria.moneytracker.ui.main.wallet

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.R
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionsAdapterDelegate : AdapterDelegate<MutableList<Any>>() {

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = items[position] is Transaction

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent.inflate(R.layout.item_transaction))

    override fun onBindViewHolder(items: MutableList<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as ViewHolder).bind(items[position] as Transaction)
    }

    private inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaction) {
            with(itemView) {
                title_text_view.text = transaction.uid
            }
        }
    }
}