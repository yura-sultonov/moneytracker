package com.allerria.moneytracker.respositories

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.allerria.moneytracker.entity.Currency
import com.allerria.moneytracker.entity.Wallet
import com.allerria.moneytracker.entity.WalletType
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import com.allerria.moneytracker.model.data.repository.WalletRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalletRepositoryTests {

    private lateinit var walletRepository: WalletRepository
    private lateinit var appDbHelper: AppDbHelper

    private val testWallet = Wallet(1, "Test", WalletType.CASH, 123.0, Currency.RUB)

    @Before
    fun load() {
        val appContext = InstrumentationRegistry.getTargetContext()
        appDbHelper = AppDbHelper(appContext)
        walletRepository = WalletRepository(appDbHelper)
    }

    @Test
    fun shouldClearWalletsDb() {
        walletRepository.clear()
        Assert.assertEquals(walletRepository.getWallets().size, 0)
    }

    @Test
    fun shouldCreateWallet() {
        walletRepository.clear()
        walletRepository.addWallet(testWallet)
        Assert.assertEquals(walletRepository.getWallets().size, 1)
    }

    @Test
    fun shouldAddAndClearWalletsDb() {
        walletRepository.addWallet(testWallet)
        walletRepository.clear()
        Assert.assertEquals(walletRepository.getWallets().size, 0)
    }

    @Test
    fun shouldFieldsEqual() {
        walletRepository.addWallet(testWallet)
        Assert.assertEquals(walletRepository.getWallets().firstOrNull()?.balance, testWallet.value)
        Assert.assertEquals(walletRepository.getWallets().firstOrNull()?.name, testWallet.name)
        Assert.assertEquals(walletRepository.getWallets().firstOrNull()?.currency, testWallet.currency)
        Assert.assertEquals(walletRepository.getWallets().firstOrNull()?.type, testWallet.type)
    }
}
