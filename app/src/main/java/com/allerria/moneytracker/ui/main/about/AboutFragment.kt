package com.allerria.moneytracker.ui.main.about

import android.content.Context
import android.os.Bundle
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.global.BaseFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject


class AboutFragment : BaseFragment(), AboutView {
    override val layoutRes = R.layout.fragment_about
    override val TAG = "ABOUT_FRAGMENT"

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