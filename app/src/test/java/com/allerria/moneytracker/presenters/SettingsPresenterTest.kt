package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.model.interactor.WalletInteractor
import com.allerria.moneytracker.ui.main.settings.SettingsPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import ru.terrakok.cicerone.Router

@RunWith(JUnit4::class)
class SettingsPresenterTest {

    private lateinit var settingsPresenter: SettingsPresenter
    private lateinit var walletInteractor: WalletInteractor
    private lateinit var router: Router

    @Before
    fun init() {
        router = mock(Router::class.java)
        walletInteractor = mock(WalletInteractor::class.java)
        settingsPresenter = SettingsPresenter(walletInteractor, router)
    }

    @Test
    fun testWipeData() {
        doNothing().`when`(walletInteractor).wipeData()
        doNothing().`when`(router).exit()
        settingsPresenter.wipeData()
        verify(walletInteractor).wipeData()
        verify(router).exit()
        verifyNoMoreInteractions(router)
        verifyNoMoreInteractions(walletInteractor)
    }
}