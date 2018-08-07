package com.allerria.moneytracker.ui.main.wallet

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.entity.TransactionCategory
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.extensions.formatMoney
import com.allerria.moneytracker.extensions.inflate
import com.allerria.moneytracker.extensions.toString
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionsAdapterDelegate : AdapterDelegate<MutableList<Transactions>>() {

    override fun isForViewType(items: MutableList<Transactions>, position: Int): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent.inflate(R.layout.item_transaction))

    override fun onBindViewHolder(items: MutableList<Transactions>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transactions) {
            with(itemView) {
                category_text_view.text = when (transaction.category) {
                    TransactionCategory.AUTO.toString() -> context.getString(R.string.auto)
                    TransactionCategory.OTHER.toString() -> context.getString(R.string.other)
                    TransactionCategory.GIFT.toString() -> context.getString(R.string.gift)
                    TransactionCategory.SALARY.toString() -> context.getString(R.string.salary)
                    TransactionCategory.HEALTH.toString() -> context.getString(R.string.health)
                    TransactionCategory.ENTERTAINMENT.toString() -> context.getString(R.string.entertainment)
                    TransactionCategory.CAFE_AND_RESTAURANT.toString() -> context.getString(R.string.cafe_and_restaurants)
                    TransactionCategory.HOUSE.toString() -> context.getString(R.string.house)
                    TransactionCategory.CLOTHING.toString() -> context.getString(R.string.clothing)
                    else -> ""
                }
                currency_text_view.text = transaction.currency.name
                value_text_view.text = transaction.amount.formatMoney()
                name_text_view.text = transaction.details
                if (name_text_view.text.isNotEmpty()) {
                    name_text_view.text = "${name_text_view.text} "
                }
                when (transaction.type) {
                    TransactionType.INCOME -> {
                        value_text_view.setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
                        value_text_view.text = "+${value_text_view.text}"
                    }
                    TransactionType.EXPENSE -> {
                        value_text_view.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
                        value_text_view.text = "-${value_text_view.text}"
                    }
                }
                date_text_view.text = transaction.date.time.toString("dd/MM/yyy")
            }
        }
    }
}