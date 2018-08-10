package com.allerria.moneytracker.ui.main.template

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Templates
import com.allerria.moneytracker.entity.TemplateType
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.extensions.formatMoney
import com.allerria.moneytracker.extensions.inflate
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_template.view.*

class TemplatesAdapterDelegate : AdapterDelegate<MutableList<Templates>>() {

    override fun isForViewType(items: MutableList<Templates>, position: Int): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(parent.inflate(R.layout.item_template))

    override fun onBindViewHolder(items: MutableList<Templates>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(template: Templates) {
            with(itemView) {
                category_text_view.text = template.transactionCategory
                currency_text_view.text = template.transactionCurrency.sign
                value_text_view.text = template.transactionAmount.formatMoney()
                when (template.transactionType) {
                    TransactionType.INCOME -> {
                        value_text_view.setTextColor(ContextCompat.getColor(context, R.color.colorGreen))
                        value_text_view.text = "+${value_text_view.text}"
                    }
                    else -> {
                        value_text_view.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
                        value_text_view.text = "-${value_text_view.text}"
                    }
                }
                template_switch.isChecked = template.enabled
                date_text_view.text = when (template.type) {
                    TemplateType.EVERY_BEGIN_DAY -> {
                        context.getString(R.string.every_day)
                    }
                    TemplateType.EVERY_BEGIN_WEEK -> {
                        context.getString(R.string.every_week)
                    }
                    TemplateType.EVERY_BEGIN_MONTH -> {
                        context.getString(R.string.every_month)
                    }
                }
            }
        }
    }
}