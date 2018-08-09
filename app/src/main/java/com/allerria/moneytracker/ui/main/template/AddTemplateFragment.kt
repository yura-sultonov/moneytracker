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
        transaction_type_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                lateinit var adapter: ArrayAdapter<String>
                when (transaction_type_spinner.selectedItem) {
                    getString(R.string.expense) -> adapter = ArrayAdapter(context, android.R.layout.simple_selectable_list_item, resources.getStringArray(R.array.transaction_expense_categories))
                    getString(R.string.income) -> adapter = ArrayAdapter(context, android.R.layout.simple_selectable_list_item, resources.getStringArray(R.array.transaction_income_categories))
                }
                //transaction_category_spinner.adapter = adapter
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        presenter.initWallets()
        add_transaction_button.setOnClickListener {
//            val transaction = createTransaction()
//            if (transaction.amount < 0) {
//                Toast.makeText(this@AddTransactionFragment.context, R.string.fill_value, Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this@AddTransactionFragment.context, R.string.success, Toast.LENGTH_LONG).show()
//                presenter.addTransaction(transaction)
//            }
        }
    }

    override fun setWallets(wallets: List<Wallets>) {

    }
}