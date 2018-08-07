package com.allerria.moneytracker.ui.main.wallet

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_wallet.*
import javax.inject.Inject

class AddWalletFragment : BaseFragment(), AddWalletView {
    override val layoutRes = R.layout.fragment_add_wallet
    override val TAG = Screens.ADD_WALLET_SCREEN

    @Inject
    @InjectPresenter
    lateinit var presenter: AddWalletPresenter

    @ProvidePresenter
    fun providePresenter(): AddWalletPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_wallet_button.setOnClickListener {
            val wallet = createWallet()
            if (wallet.name == "") {
                Toast.makeText(this@AddWalletFragment.context, R.string.fill_name, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@AddWalletFragment.context, R.string.success, Toast.LENGTH_LONG).show()
                presenter.addWallet(wallet)
            }
        }
    }

    override fun onDestroy() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity?.currentFocus
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        super.onDestroy()
    }

    private fun createWallet(): Wallet {
        val walletName = wallet_name_edit_text.text.toString()
        val walletCurrency = when (wallet_currency_spinner.selectedItem.toString()) {
            getString(R.string.rub) -> Currency.RUB
            getString(R.string.usd) -> Currency.USD
            getString(R.string.eur) -> Currency.EUR
            else -> Currency.USD
        }
        val walletValue = if (wallet_initial_balance_edit_text.text.isNotEmpty()) wallet_initial_balance_edit_text.text.toString().toDouble() else 0.0
        val walletType = when (wallet_type_spinner.selectedItem.toString()) {
            getString(R.string.card) -> WalletType.CARD
            getString(R.string.cash) -> WalletType.CASH
            else -> WalletType.CASH
        }
        return Wallet(10, walletName, walletType, walletValue, walletCurrency)
    }
}