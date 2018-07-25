package com.allerria.moneytracker.ui.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.common.BaseActivity
import com.allerria.moneytracker.ui.common.BaseFragment
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val BALANCE_FRAGMENT = "BALANCE_FRAGMENT"
        const val ABOUT_FRAGMENT = "ABOUT_FRAGMENT"
        const val SETTINGS_FRAGMENT = "SETTINGS_FRAGMENT"
    }

    @Inject
    lateinit var app: Context

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = MainPresenter()

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_frame, BalanceFragment())
                    .commitNow()
        }

        setSupportActionBar(toolbar)
        nav_view.setNavigationItemSelectedListener(this)
        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toolbar.setNavigationOnClickListener { onBackPressed() }


        onFragmentChanged((supportFragmentManager.fragments.first() as BaseFragment).TAG)

        supportFragmentManager.addOnBackStackChangedListener {
            onFragmentChanged((supportFragmentManager.fragments.first() as BaseFragment).TAG)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if ((supportFragmentManager.fragments.last() as BaseFragment).TAG == BALANCE_FRAGMENT) {
                drawer_layout.openDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        changeFragment(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun changeFragment(Id: Int) {
        lateinit var fragment: BaseFragment
        when (Id) {
            R.id.nav_about -> {
                fragment = AboutFragment()
            }
            R.id.nav_balance -> {
                fragment = BalanceFragment()
            }
            R.id.nav_settings -> {
                fragment = SettingsFragment()
            }
        }
        if ((supportFragmentManager.fragments.first() as BaseFragment).TAG != fragment.TAG) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .addToBackStack(fragment.TAG)
                    .commit()
        }
    }

    private fun onFragmentChanged(TAG: String) {
        when (TAG) {
            BALANCE_FRAGMENT -> {
                supportActionBar?.setTitle(R.string.balance)
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                toggle.syncState()
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                nav_view.menu.getItem(0).isChecked = true
            }
            SETTINGS_FRAGMENT -> {
                supportActionBar?.setTitle(R.string.settings)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            ABOUT_FRAGMENT -> {
                supportActionBar?.setTitle(R.string.about)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

}
