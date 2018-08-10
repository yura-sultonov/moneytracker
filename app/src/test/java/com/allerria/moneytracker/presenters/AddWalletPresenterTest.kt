package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.wallet.AddWalletPresenter
import com.allerria.moneytracker.ui.main.wallet.AddWalletView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import ru.terrakok.cicerone.Router

@RunWith(JUnit4::class)
class AddWalletPresenterTest {

    private lateinit var addWalletPresenter: AddWalletPresenter
    private lateinit var addWalletView: AddWalletView
    private lateinit var walletInteractor: WalletInteractor
    private lateinit var router: Router

    private val testWallet = Wallet(1, "Test", WalletType.CASH, 123.0, Currency.RUB)

    @Before
    fun init() {
        walletInteractor = Mockito.mock(WalletInteractor::class.java)
        router = Mockito.mock(Router::class.java)
        addWalletView = Mockito.mock(AddWalletView::class.java)

        addWalletPresenter = AddWalletPresenter(walletInteractor, router)
        addWalletPresenter.attachView(addWalletView)
    }

    @Test
    fun testAddWallet() {
        doNothing().`when`(walletInteractor).addWallet(testWallet)
        doNothing().`when`(router).backTo(Screens.BALANCE_SCREEN)
        addWalletPresenter.addWallet(testWallet)
        verify(walletInteractor).addWallet(testWallet)
        verify(router).backTo(Screens.BALANCE_SCREEN)
        verifyNoMoreInteractions(router)
        verifyNoMoreInteractions(walletInteractor)
    }

}