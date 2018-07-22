package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.model.FinanceManager
import com.allerria.moneytracker.ui.global.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import javax.inject.Inject

class BalanceFragment : BaseFragment(), BalanceView {

    override val layoutRes = R.layout.fragment_balance
    override val TAG = "BALANCE_FRAGMENT"

    @Inject
    lateinit var app : Context

    @Inject
    lateinit var financeManager: FinanceManager

    @InjectPresenter
    lateinit var presenter: BalancePresenter

    @ProvidePresenter
    fun providePresenter(): BalancePresenter = BalancePresenter(financeManager)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showBalance()
    }

    override fun showBalance(balanceInRates: List<Money>) {
        rubles_text_view.text = "${balanceInRates.find { it.currency == "RUB" }?.value} P"
        dollars_text_view.text = "${balanceInRates.find { it.currency == "USD" }?.value} $"
    }
}
