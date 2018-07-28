package com.allerria.moneytracker.ui.main.transaction

import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.common.BaseDialogFragment
import com.arellomobile.mvp.MvpDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_about.view.*
import javax.inject.Inject

class AddTransactionDialogFragment : BaseDialogFragment(), AddTransactionView {

    override val TAG = Screens.ADD_TRANSACTION_SCREEN
    override val layoutRes = R.layout.fragment_add_transaction

    @Inject
    @InjectPresenter
    lateinit var presenter: AddTransactionPresenter

    @ProvidePresenter
    fun provideAddTransactionPresenter(): AddTransactionPresenter {
        return presenter
    }
}