package com.allerria.moneytracker.ui.main.info

import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject

class InfoFragment : BaseFragment(), InfoView {
    override val layoutRes = R.layout.fragment_info
    override val TAG = Screens.INFO_SCREEN

    @Inject
    @InjectPresenter
    lateinit var presenter: InfoPresenter

    @ProvidePresenter
    fun providePresenter(): InfoPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initInfo()
    }

    override fun setBalanceSum(balanceSum: String) {
        balance_sum_tv.text = balanceSum
    }

    override fun setMonthExpense(expenseSum: String) {
        month_exponse_tv.text = expenseSum
    }

    override fun setMonthIncome(incomeSum: String) {
        month_income_tv.text = incomeSum
    }

}