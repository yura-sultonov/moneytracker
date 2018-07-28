package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Money
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BalanceFragment : BaseFragment(), BalanceView {

    override val layoutRes = R.layout.fragment_balance
    override val TAG = Screens.BALANCE_SCREEN

    private lateinit var balanceViewPagerAdapter: BalanceViewPagerAdapter

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var app: Context

    @Inject
    @InjectPresenter
    lateinit var presenter: BalancePresenter

    @ProvidePresenter
    fun providePresenter(): BalancePresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balanceViewPagerAdapter = BalanceViewPagerAdapter()
        balance_view_pager.adapter = balanceViewPagerAdapter
        balance_tab_layout.setupWithViewPager(balance_view_pager)
        add_transaction_button.setOnClickListener { router.navigateTo(Screens.ABOUT_SCREEN) }
        presenter.showBalance()
    }

    override fun showBalance(balanceInRates: List<Money>) {
        balanceViewPagerAdapter.setData(balanceInRates)
    }
}
