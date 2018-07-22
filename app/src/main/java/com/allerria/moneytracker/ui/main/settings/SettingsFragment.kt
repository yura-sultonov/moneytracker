package com.allerria.moneytracker.ui.main.settings

import android.content.Context
import android.os.Bundle
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.global.BaseFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject


class SettingsFragment : BaseFragment(), SettingsView {

    override val layoutRes = R.layout.fragment_settings
    override val TAG = "SETTINGS_FRAGMENT"

    @Inject
    lateinit var app : Context

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = SettingsPresenter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

}
