package com.allerria.moneytracker.ui.main.settings

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class SettingsFragment : BaseFragment(), SettingsView {

    override val layoutRes = R.layout.fragment_settings
    override val TAG = Screens.SETTINGS_SCREEN

    @Inject
    lateinit var app: Context

    @Inject
    lateinit var router: Router

    @Inject
    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    private lateinit var dialogBuilder: AlertDialog.Builder

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogBuilder = AlertDialog.Builder(this@SettingsFragment.activity)
        dialogBuilder.setTitle(R.string.data_deletion)
                .setMessage(R.string.are_you_sure_to_delete)
                .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { _, _ -> presenter.wipeData() })
                .setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { _, _ -> })
        wipe_data_text_view.setOnClickListener {
            dialogBuilder.show()
        }
        add_wallet_text_view.setOnClickListener { router.navigateTo(Screens.ADD_WALLET_SCREEN) }
    }
}
