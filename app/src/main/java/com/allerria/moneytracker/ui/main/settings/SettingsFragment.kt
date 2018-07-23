package com.allerria.moneytracker.ui.main.settings

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.MoneyTrackerApp
import com.allerria.moneytracker.R
import com.allerria.moneytracker.model.FinanceManager
import com.allerria.moneytracker.ui.global.BaseFragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject


class SettingsFragment : BaseFragment(), SettingsView {

    override val layoutRes = R.layout.fragment_settings
    override val TAG = "SETTINGS_FRAGMENT"

    @Inject
    lateinit var app: Context

    @Inject
    lateinit var financeManager: FinanceManager

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    private lateinit var dialogBuilder: AlertDialog.Builder

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = SettingsPresenter(financeManager)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        MoneyTrackerApp.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogBuilder = AlertDialog.Builder(this@SettingsFragment.activity)
        dialogBuilder.setTitle(R.string.data_deletion)
                .setMessage(R.string.are_you_sure_to_delete)
                .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialogInterface, i -> presenter.wipeData() })
                .setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { dialogInterface, i -> })
        wipe_data_text_view.setOnClickListener {
            dialogBuilder.show()
        }
    }

}
