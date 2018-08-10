package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Wallets
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Template
import com.allerria.moneytracker.entity.TemplateType
import com.allerria.moneytracker.entity.TransactionType
import com.allerria.moneytracker.model.data.repository.TemplateRepository
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.template.AddTemplatePresenter
import com.allerria.moneytracker.ui.main.template.AddTemplateView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import ru.terrakok.cicerone.Router
import java.util.*

@RunWith(JUnit4::class)
class AddTemplatePresenterTest {

    private lateinit var addTemplatePresenter: AddTemplatePresenter

    private lateinit var walletInteractor: WalletInteractor
    private lateinit var templatesRepository: TemplateRepository
    private lateinit var router: Router
    private lateinit var addTemplateView: AddTemplateView

    private val testTemplate = Template(TemplateType.EVERY_BEGIN_MONTH, false, TransactionType.INCOME, "", Currency.RUB, 0.0, 0, Calendar.getInstance().time)

    @Before
    fun init() {
        walletInteractor = mock(WalletInteractor::class.java)
        templatesRepository = mock(TemplateRepository::class.java)
        router = mock(Router::class.java)
        addTemplateView = mock(AddTemplateView::class.java)

        addTemplatePresenter = AddTemplatePresenter(walletInteractor, templatesRepository, router)
        addTemplatePresenter.attachView(addTemplateView)
    }

    @Test
    fun testInitWallets() {
        doReturn(listOf<Wallets>()).`when`(walletInteractor).getWallets()
        doNothing().`when`(addTemplateView).setWallets(listOf())
        addTemplatePresenter.initWallets()
        verify(addTemplateView).setWallets(listOf())
        verify(walletInteractor).getWallets()
        verifyNoMoreInteractions(walletInteractor)
        verifyNoMoreInteractions(addTemplateView)
    }

    @Test
    fun testAddTemplate() {
        doNothing().`when`(templatesRepository).insertTemplate(testTemplate)
        doNothing().`when`(router).exit()
        addTemplatePresenter.addTemplate(testTemplate)
        verify(templatesRepository).insertTemplate(testTemplate)
        verify(router).exit()
        verifyNoMoreInteractions(router)
        verifyNoMoreInteractions(templatesRepository)
    }

    @Test
    fun testTypeTransactionChangeIncome() {
        doReturn(listOf<String>()).`when`(walletInteractor).getIncomeCategories()
        doNothing().`when`(addTemplateView).setCategories(listOf())
        addTemplatePresenter.typeTransactionChange(1)
        verify(addTemplateView).setCategories(listOf())
        verify(walletInteractor).getIncomeCategories()
    }

    @Test
    fun testTypeTransactionChangeExpense() {
        doReturn(listOf<String>()).`when`(walletInteractor).getExpenseCategories()
        doNothing().`when`(addTemplateView).setCategories(listOf())
        addTemplatePresenter.typeTransactionChange(0)
        verify(addTemplateView).setCategories(listOf())
        verify(walletInteractor).getExpenseCategories()
    }

}