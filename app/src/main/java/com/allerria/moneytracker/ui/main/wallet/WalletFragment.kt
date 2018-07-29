package com.allerria.moneytracker.ui.main.wallet

import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallet.*
import kotlinx.android.synthetic.main.item_wallet.view.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class WalletFragment : BaseFragment(), WalletView {

    override val layoutRes = R.layout.fragment_wallet
    override val TAG = Screens.WALLET_SCREEN

    companion object {
        fun newInstance(uid: String) = WalletFragment().apply {
            Timber.d(uid)
            arguments = Bundle().apply { putString(TAG, uid) }
        }
    }

    private val transactionsAdapter by lazy { TransactionsAdapter() }

    lateinit var walletUid: String

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletPresenter

    @ProvidePresenter
    fun providePresenter(): WalletPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        walletUid = arguments?.getString(TAG) ?: ""
        transactions_recycler_view.adapter = transactionsAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.initView(walletUid)
    }

    override fun loadTransactions(transactions: List<Transaction>) {
        transactionsAdapter.setData(transactions)
    }

    override fun loadWallet(wallet: Wallet) {
        Timber.d(wallet.toString())
        with(item_wallet) {
            add_wallet_image_button.visibility = View.GONE
            type_text_view.visibility = View.VISIBLE
            balance_text_view.visibility = View.VISIBLE
            type_text_view.text = wallet.type.name
            balance_text_view.text = wallet.value.toString() + wallet.currency.sign
        }
    }

}