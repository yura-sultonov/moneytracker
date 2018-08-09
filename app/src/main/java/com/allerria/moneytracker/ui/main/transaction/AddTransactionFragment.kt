package com.allerria.moneytracker.ui.main.transaction

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.*
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import java.util.*
import javax.inject.Inject

class AddTransactionFragment : BaseFragment(), AddTransactionView {

    override val TAG = Screens.ADD_TRANSACTION_SCREEN
    override val layoutRes = R.layout.fragment_add_transaction
    lateinit var localWallets: List<Wallets>
    lateinit var transactionType: TransactionType

    companion object {
        fun newInstance(transactionType: TransactionType): AddTransactionFragment {
            val fragment = AddTransactionFragment()
            val args = Bundle()
            args.putString("type", transactionType.toString())
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: AddTransactionPresenter

    @ProvidePresenter
    fun providePresenter(): AddTransactionPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction_type_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.typeTransactionChange(transaction_type_spinner.selectedItemPosition)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        presenter.initWallets()
        add_transaction_button.setOnClickListener {
            val transaction = createTransaction()
            if (transaction.amount < 0) {
                Toast.makeText(this@AddTransactionFragment.context, R.string.fill_value, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@AddTransactionFragment.context, R.string.success, Toast.LENGTH_LONG).show()
                presenter.addTransaction(transaction)
            }
        }
    }

    override fun onDestroy() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity?.currentFocus
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        super.onDestroy()
    }

    override fun setWallets(wallets: kotlin.collections.List<com.allerria.moneytracker.Wallets>) {
        transaction_wallet_spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, wallets.map { it ->
            val type = when (it.type) {
                WalletType.CARD -> getString(R.string.card)
                WalletType.CASH -> getString(R.string.cash)
            }
            "${it.name} - $type - ${it.currency.sign}"
        })
        localWallets = wallets
    }

    override fun setCategories(categories: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, categories)
        transaction_category_autocomplete.setAdapter(adapter)
    }

    private fun createTransaction(): Transaction {
        lateinit var transactionType: TransactionType
        lateinit var money: Money
        when (transaction_type_spinner.selectedItem.toString()) {
            getString(R.string.expense) -> transactionType = TransactionType.EXPENSE
            getString(R.string.income) -> transactionType = TransactionType.INCOME
            else -> TransactionType.EXPENSE
        }
        val transactionCategory = transaction_category_autocomplete.text.toString()
        val details: String = transaction_details_edit_text.text.toString()

        val wallet: Wallets = localWallets.find {
            val type = when (it.type) {
                WalletType.CARD -> getString(R.string.card)
                WalletType.CASH -> getString(R.string.cash)
            }
            "${it.name} - $type - ${it.currency.sign}" == transaction_wallet_spinner.selectedItem.toString()
        }!!
        val transactionValue = if (transaction_value_edit_text.text.toString().isNotEmpty()) transaction_value_edit_text.text.toString() else "-1.0"
        money = Money(wallet.currency, transactionValue.toDouble())
        return Transaction(10, transactionType, transactionCategory, money.currency, money.value, wallet.id, details, Calendar.getInstance().time)
    }
}