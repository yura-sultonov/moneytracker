package com.allerria.moneytracker.ui.main.wallet

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.Transactions
import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.extensions.formatMoney
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_wallet.*
import timber.log.Timber
import javax.inject.Inject

class WalletFragment : BaseFragment(), WalletView {

    override val layoutRes = R.layout.fragment_wallet
    override val TAG = Screens.WALLET_SCREEN

    companion object {
        fun newInstance(id: Long) = WalletFragment().apply {
            Timber.d(id.toString())
            arguments = Bundle().apply { putLong(TAG, id) }
        }
    }

    private val transactionsAdapter by lazy { TransactionsAdapter() }

    private var walletId: Long = -1

    @Inject
    @InjectPresenter
    lateinit var presenter: WalletPresenter

    @ProvidePresenter
    fun providePresenter(): WalletPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        walletId = arguments?.getLong(TAG) ?: -1
        with(transactions_recycler_view) {
            adapter = transactionsAdapter
            setHasFixedSize(true)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)

                    val position = parent.getChildAdapterPosition(view)
                    val last = parent.adapter!!.itemCount - 1

                    outRect.top = resources.getDimension(R.dimen.margin_8).toInt()
                    outRect.left = resources.getDimension(R.dimen.margin_16).toInt()
                    outRect.right = resources.getDimension(R.dimen.margin_16).toInt()

                    if (position == 0) {
                        outRect.top = resources.getDimension(R.dimen.margin_20).toInt()
                    }

                    if (position == last) {
                        outRect.bottom = resources.getDimension(R.dimen.margin_20).toInt()
                    }
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.initView(walletId)
    }

    override fun loadWallet(wallet: Wallets, transactions: List<Transactions>) {
        with(wallet_card_view) {
            when (wallet.type) {
                WalletType.CASH -> wallet_image_view.setImageDrawable(context.getDrawable(R.drawable.ic_wallet_cash))
                WalletType.CARD -> wallet_image_view.setImageDrawable(context.getDrawable(R.drawable.ic_wallet_card))
            }
            name_text_view.text = wallet.name
            val balanceText = wallet.balance.formatMoney() + wallet.currency.sign
            balance_text_view.text = balanceText
            if (transactions.isEmpty()) {
                show_charts_image_button.visibility = View.GONE
            } else {
                show_charts_image_button.visibility = View.VISIBLE
                show_charts_image_button.setOnClickListener {
                    val walletChartDialogFragment = WalletChartDialogFragment.newInstance(wallet.id)
                    walletChartDialogFragment.show(childFragmentManager, Screens.WALLET_CHART_SCREEN)
                }
            }
        }
        transactionsAdapter.setData(transactions)
    }

}