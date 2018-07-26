package com.allerria.moneytracker.ui.main.about

import android.content.Context
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseFragment
import com.allerria.moneytracker.ui.main.MainActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject


class AboutFragment : BaseFragment(), AboutView {
    override val layoutRes = R.layout.fragment_about
    override val TAG = Screens.ABOUT_SCREEN

    @Inject
    lateinit var app: Context

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    @ProvidePresenter
    fun providePresenter(): AboutPresenter = AboutPresenter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

}