package com.allerria.moneytracker.ui.main.template

import com.allerria.moneytracker.entity.Template
import com.allerria.moneytracker.model.data.repository.TemplateRepository
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AddTemplatePresenter @Inject constructor(private val walletInteractor: WalletInteractor, private val templateRepository: TemplateRepository, private val router: Router) : MvpPresenter<AddTemplateView>() {
    fun initWallets() {
        viewState.setWallets(walletInteractor.getWallets())
    }

    fun addTemplate(template: Template){
        templateRepository.insertTemplate(template)
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