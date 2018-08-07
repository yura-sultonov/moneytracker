package com.allerria.moneytracker.ui.main.wallet

import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.extensions.formatMoney
import com.allerria.moneytracker.ui.common.BaseDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.hadiidbouk.charts.BarData
import kotlinx.android.synthetic.main.fragment_chart.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class WalletChartDialogFragment : BaseDialogFragment(), WalletChartView {
    override val layoutRes: Int = R.layout.fragment_chart
    override val TAG: String = Screens.WALLET_CHART_SCREEN

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletChartPresenter

    @ProvidePresenter
    fun providePresenter(): WalletChartPresenter {
        return presenter
    }

    companion object {
        fun newInstance(id: Long) = WalletChartDialogFragment().apply {
            arguments = Bundle().apply { putLong(TAG, id) }
        }
    }

    lateinit var walletUid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        walletUid = arguments?.getString(TAG) ?: ""
        Timber.d(walletUid)
        presenter.buildChart(1)
        transaction_type_radio_group.setOnCheckedChangeListener { _, _ -> presenter.buildChart(1) }
    }

    override fun buildChart(transactions: kotlin.collections.List<com.allerria.moneytracker.Transactions>) {
        val dataList = ArrayList<BarData>()
        val currency = transactions.first().currency.sign
        if (expense_type_radio_button.isChecked) {
            transactions.filter { it.type == TransactionType.EXPENSE }.groupBy { it.category }.forEach {
                var value: Float = 0.0f
                it.value.forEach { value += it.amount.toFloat() }
                val data = BarData(it.key, value, value.formatMoney() + currency)
                dataList.add(data)
            }
        } else {
            transactions.filter { it.type == TransactionType.INCOME }.groupBy { it.category }.forEach {
                var value: Float = 0.0f
                it.value.forEach { value += it.amount.toFloat() }
                val data = BarData(it.key, value, value.formatMoney() + currency)
                dataList.add(data)
            }
        }
        ChartProgressBar.setDataList(dataList)
        ChartProgressBar.setMaxValue(transactions.maxBy { it.amount }!!.amount.toFloat())
        ChartProgressBar.build()
    }
}