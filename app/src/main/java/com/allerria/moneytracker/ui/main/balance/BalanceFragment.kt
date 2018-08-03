package com.allerria.moneytracker.ui.main.balance

import android.content.Context
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class BalanceFragment : BaseFragment(), BalanceView {

    companion object {
        const val VIEW_PAGE = "view_page"
    }

    override val layoutRes = R.layout.fragment_balance
    override val TAG = Screens.BALANCE_SCREEN

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var app: Context

    @Inject
    @InjectPresenter
    lateinit var presenter: BalancePresenter

    private lateinit var balanceViewPagerAdapter: BalanceViewPagerAdapter

    private var viewPagerItem = 0

    @ProvidePresenter
    fun providePresenter(): BalancePresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        balanceViewPagerAdapter = BalanceViewPagerAdapter(childFragmentManager)
        balance_view_pager.adapter = balanceViewPagerAdapter
        balance_view_pager.offscreenPageLimit = 3
        add_transaction_button.setOnClickListener {
            router.navigateTo(Screens.ADD_TRANSACTION_SCREEN)
        }
    }

    override fun onResume() {
        super.onResume()
        balance_view_pager.currentItem = viewPagerItem
        balance_tab_layout.setupWithViewPager(balance_view_pager)
        presenter.showBalance()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (balance_view_pager != null) {
            outState.putInt(VIEW_PAGE, balance_view_pager.currentItem)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            viewPagerItem = savedInstanceState.getInt(VIEW_PAGE)
        }
    }

    override fun showBalance(wallets: kotlin.collections.List<com.allerria.moneytracker.Wallets>) {
        balanceViewPagerAdapter.setData(wallets)
        if (wallets.isNotEmpty() && add_wallet_button.visibility != View.GONE) {
            add_wallet_button.visibility = View.GONE
            add_transaction_button.setOnClickListener {
                router.navigateTo(Screens.ADD_TRANSACTION_SCREEN)
            }
        }
    }

    override fun refreshWallets() {
        balanceViewPagerAdapter = BalanceViewPagerAdapter(childFragmentManager)
        balance_view_pager.adapter = balanceViewPagerAdapter
        balance_view_pager.currentItem = viewPagerItem
        balance_tab_layout.setupWithViewPager(balance_view_pager)
        add_wallet_button.visibility = View.VISIBLE
        add_transaction_button.setOnClickListener { router.navigateTo(Screens.ADD_WALLET_SCREEN) }
    }

}
