package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.di.AppComponent
import com.allerria.moneytracker.di.AppModule
import com.allerria.moneytracker.model.BalanceManager
import com.allerria.moneytracker.ui.global.BaseFragment
import com.arellomobile.mvp.MvpAppCompatFragment
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
    lateinit var balanceManager: BalanceManager

    @InjectPresenter
    lateinit var presenter: BalancePresenter

    @ProvidePresenter
    fun providePresenter(): BalancePresenter = BalancePresenter(balanceManager)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showBalance()
    }

    override fun showBalance(rubles: Double, dollars: Double) {
        rubles_text_view.text = "$rubles P"
        dollars_text_view.text = "$dollars $"
    }
}
