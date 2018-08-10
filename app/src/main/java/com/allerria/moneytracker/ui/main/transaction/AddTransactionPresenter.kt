package com.allerria.moneytracker.ui.main.transaction

import com.allerria.moneytracker.entity.Transaction
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AddTransactionPresenter @Inject constructor(private val walletInteractor: WalletInteractor, private val router: Router) : MvpPresenter<AddTransactionView>() {

    fun initWallets() {
        viewState.setWallets(walletInteractor.getWallets())
    }

    fun addTransaction(transaction: Transaction) {
        walletInteractor.executeTransaction(transaction)
        router.exit()
    }

    fun typeTransactionChange(selectedItem: Int) {
        lateinit var categories: List<String>
        when (selectedItem) {
            1 -> categories = walletInteractor.getIncomeCategories()
            0 -> categories = walletInteractor.getExpenseCategories()
        }
        viewState.setCategories(categories)
    }
}