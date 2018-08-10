package com.allerria.moneytracker.ui.main.template

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
import com.allerria.moneytracker.entity.Template
import com.allerria.moneytracker.entity.TemplateType
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_template.*
import java.util.*
import javax.inject.Inject

class AddTemplateFragment : BaseFragment(), AddTemplateView {

    override val layoutRes = R.layout.fragment_add_template
    override val TAG = Screens.ADD_TEMPLATE_SCREEN
    lateinit var localWallets: List<Wallets>

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
        template_transaction_type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.typeTransactionChange(template_transaction_type_spinner.selectedItemPosition)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        add_template_transaction_button.setOnClickListener {
            val template = createTemplate()
            if (template.transactionAmount < 0) {
                Toast.makeText(context, R.string.fill_value, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.success, Toast.LENGTH_LONG).show()
                presenter.addTemplate(template)
            }
        }
    }

    private fun createTemplate(): Template {
        val periodType: TemplateType = when (template_transaction_type_spinner.selectedItemPosition) {
            0 -> {
                TemplateType.EVERY_BEGIN_DAY
            }
            1 -> {
                TemplateType.EVERY_BEGIN_WEEK
            }
            else -> {
                TemplateType.EVERY_BEGIN_MONTH
            }
        }
        val transactionType: TransactionType = when (template_transaction_type_spinner.selectedItem.toString()) {
            getString(R.string.expense) -> TransactionType.EXPENSE
            getString(R.string.income) -> TransactionType.INCOME
            else -> TransactionType.EXPENSE
        }
        val transactionCategory = template_transaction_category_autocomplete.text.toString()

        val wallet: Wallets = localWallets.find {
            val type = when (it.type) {
                WalletType.CARD -> getString(R.string.card)
                WalletType.CASH -> getString(R.string.cash)
            }
            "${it.name} - $type - ${it.currency.sign}" == template_transaction_wallet_spinner.selectedItem.toString()
        }!!
        val transactionValue = template_transaction_value_edit_text.text.toString().toDouble()
        return Template(periodType, true, transactionType, transactionCategory, wallet.currency, transactionValue, wallet.id, GregorianCalendar().time)
    }

    override fun onDestroy() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity?.currentFocus
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        super.onDestroy()
    }

    override fun setWallets(wallets: List<Wallets>) {
        template_transaction_wallet_spinner.adapter = ArrayAdapter<String>(context, android.R.layout.simple_selectable_list_item, wallets.map { it ->
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
        template_transaction_category_autocomplete.setAdapter(adapter)
    }
}