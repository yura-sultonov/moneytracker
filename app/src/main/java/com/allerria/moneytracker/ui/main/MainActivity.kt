package com.allerria.moneytracker.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseActivity
import com.allerria.moneytracker.ui.common.BaseFragment
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.info.InfoFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import com.allerria.moneytracker.ui.main.transaction.AddTransactionFragment
import com.allerria.moneytracker.ui.main.wallet.AddWalletFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import timber.log.Timber
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    override val layoutRes = R.layout.activity_main

    @Inject
    lateinit var app: Context

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var router: Router

    @ProvidePresenter
    fun providePresenter(): MainPresenter = MainPresenter()

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.BALANCE_SCREEN)
        }
        initView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_info -> {
                router.navigateTo(Screens.INFO_SCREEN)
            }
            R.id.nav_balance -> {
                router.navigateTo(Screens.BALANCE_SCREEN)
            }
            R.id.nav_about -> {
                router.navigateTo(Screens.ABOUT_SCREEN)
            }
            R.id.nav_settings -> {
                router.navigateTo(Screens.SETTINGS_SCREEN)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        nav_view.setNavigationItemSelectedListener(this)
        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toolbar.setNavigationOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                val tag = (supportFragmentManager.fragments.first() as BaseFragment).TAG
                if (tag != Screens.INFO_SCREEN && tag != Screens.BALANCE_SCREEN && tag != Screens.SETTINGS_SCREEN && tag != Screens.ABOUT_SCREEN) {
                    router.exit()
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        }
        // drawer animation
        drawer_layout.drawerElevation = 0F
        drawer_layout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val moveFactor = nav_view.width * slideOffset
                main_container.translationX = moveFactor
                main_container.scaleX = 1 - slideOffset / 4
                main_container.scaleY = 1 - slideOffset / 4
            }
        })
        supportFragmentManager.addOnBackStackChangedListener {
            onChangeFragment((supportFragmentManager.fragments.first() as BaseFragment).TAG)
        }
        //toolbar setting
        if (supportFragmentManager.fragments.size > 0) {
            onChangeFragment((supportFragmentManager.fragments.first() as BaseFragment).TAG)
        } else {
            onChangeFragment("init")
        }
    }

    private fun onChangeFragment(tag: String) {
        Timber.d(tag)
        var isRoot = false
        when (tag) {
            "init" -> {
                nav_view.menu.getItem(0).isChecked = true
                isRoot = true
            }
            Screens.BALANCE_SCREEN -> {
                nav_view.menu.getItem(0).isChecked = true
                isRoot = true
            }
            Screens.INFO_SCREEN -> {
                nav_view.menu.getItem(1).isChecked = true
                isRoot = true
            }
            Screens.SETTINGS_SCREEN -> {
                nav_view.menu.getItem(2).isChecked = true
                isRoot = true
            }
            Screens.ABOUT_SCREEN -> {
                nav_view.menu.getItem(3).isChecked = true
                isRoot = true
            }
        }
        if (isRoot) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            toggle.syncState()
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }

        val titleInt: Int = when (tag) {
            Screens.SETTINGS_SCREEN -> R.string.settings
            Screens.ABOUT_SCREEN -> R.string.about
            Screens.ADD_TRANSACTION_SCREEN -> R.string.add_transaction
            Screens.ADD_WALLET_SCREEN -> R.string.add_wallet
            Screens.INFO_SCREEN -> R.string.info
            else -> R.string.balance
        }

        supportActionBar?.setTitle(titleInt)
    }

    override val navigator = object : SupportAppNavigator(this, R.id.main_container) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            Screens.BALANCE_SCREEN -> BalanceFragment()
            Screens.ABOUT_SCREEN -> AboutFragment()
            Screens.SETTINGS_SCREEN -> SettingsFragment()
            Screens.ADD_TRANSACTION_SCREEN -> AddTransactionFragment()
            Screens.ADD_WALLET_SCREEN -> AddWalletFragment()
            Screens.INFO_SCREEN -> InfoFragment()
            else -> null
        }

        override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
            fragmentTransaction?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        }
    }
}
