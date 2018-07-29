package com.allerria.moneytracker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseActivity
import com.allerria.moneytracker.ui.common.BaseFragment
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import com.allerria.moneytracker.ui.main.transaction.AddTransactionFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
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
                if ((supportFragmentManager.fragments.first() as BaseFragment).TAG != Screens.BALANCE_SCREEN) {
                    router.backTo(Screens.BALANCE_SCREEN)
                } else {
                    drawer_layout.openDrawer(GravityCompat.START)
                }
            }
        }
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
        when (tag) {
            Screens.SETTINGS_SCREEN -> {
                supportActionBar?.setTitle(R.string.settings)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            Screens.ABOUT_SCREEN -> {
                supportActionBar?.setTitle(R.string.about)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            Screens.BALANCE_SCREEN, "init" -> {
                supportActionBar?.setTitle(R.string.balance)
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toggle.syncState()
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                nav_view.menu.getItem(0).isChecked = true
            }
            Screens.ADD_TRANSACTION_SCREEN -> {
                supportActionBar?.setTitle(R.string.add_transaction)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override val navigator = object : SupportAppNavigator(this, R.id.main_container) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            Screens.BALANCE_SCREEN -> BalanceFragment()
            Screens.ABOUT_SCREEN -> AboutFragment()
            Screens.SETTINGS_SCREEN -> SettingsFragment()
            Screens.ADD_TRANSACTION_SCREEN -> AddTransactionFragment()
            else -> null
        }
    }
}
