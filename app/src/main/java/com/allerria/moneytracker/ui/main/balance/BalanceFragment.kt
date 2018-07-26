package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.model.interactor.FinanceManagerInteractor
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import javax.inject.Inject

class BalanceFragment : BaseFragment(), BalanceView {

    override val layoutRes = R.layout.fragment_balance
    override val TAG = Screens.BALANCE_SCREEN

    @Inject
    lateinit var app: Context

    @Inject
    lateinit var financeManagerInteractor: FinanceManagerInteractor

    @InjectPresenter
    lateinit var presenter: BalancePresenter

    @ProvidePresenter
    fun providePresenter(): BalancePresenter = BalancePresenter(financeManagerInteractor)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usd_text_view.setOnClickListener { presenter.updateCurrenciesRate() }
        presenter.showBalance()
    }

    override fun showBalance(balanceInRates: List<Money>) {
        rub_text_view.text = "${balanceInRates.find { it.currency == Currency.RUB }?.value} \u20BD"
        usd_text_view.text = "${balanceInRates.find { it.currency == Currency.USD }?.value} $"
    }
}
