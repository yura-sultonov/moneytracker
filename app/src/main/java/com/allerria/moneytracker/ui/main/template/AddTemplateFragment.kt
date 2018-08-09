package com.allerria.moneytracker.ui.main.template

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import javax.inject.Inject

class AddTemplateFragment : BaseFragment(), AddTemplateView {

    override val layoutRes = R.layout.fragment_add_template
    override val TAG = Screens.ADD_TEMPLATE_SCREEN
    lateinit var listWallets: List<Wallets>

    @Inject
    @InjectPresenter
    lateinit var presenter: AddTemplatePresenter

    @ProvidePresenter
    fun providePresenter(): AddTemplatePresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initWallets()
    }

    override fun setWallets(wallets: List<Wallets>) {

    }
}