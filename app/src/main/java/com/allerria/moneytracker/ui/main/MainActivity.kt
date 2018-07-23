package com.allerria.moneytracker.ui.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.global.BaseActivity
import com.allerria.moneytracker.ui.global.BaseFragment
import com.allerria.moneytracker.ui.main.about.AboutFragment
import com.allerria.moneytracker.ui.main.balance.BalanceFragment
import com.allerria.moneytracker.ui.main.settings.SettingsFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var app: Context

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_frame, BalanceFragment())
                    .commit()
        }

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
        toolbar.setTitle(R.string.balance)

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.fragments.size > 0) {
                when ((supportFragmentManager.fragments.last() as BaseFragment).TAG) {
                    "BALANCE_FRAGMENT" -> {

                        toolbar.setTitle(R.string.balance)
                        nav_view.menu.getItem(0).isChecked = true
                        if (supportFragmentManager.backStackEntryCount <= 1) {
                            supportActionBar?.setDisplayHomeAsUpEnabled(false)
                            toggle.syncState()
                            toolbar.setNavigationOnClickListener {
                                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                                    drawer_layout.closeDrawer(GravityCompat.START)
                                } else {
                                    drawer_layout.openDrawer(GravityCompat.START)
                                }
                            }
                        }
                    }
                    "SETTINGS_FRAGMENT" -> {
                        toolbar.setTitle(R.string.settings)
                        nav_view.menu.getItem(1).isChecked = true
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        toolbar.setNavigationOnClickListener {
                            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                                drawer_layout.closeDrawer(GravityCompat.START)
                            } else {
                                super.onBackPressed()
                            }
                        }                    }
                    "ABOUT_FRAGMENT" -> {
                        toolbar.setTitle(R.string.about)
                        nav_view.menu.getItem(2).isChecked = true
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                            drawer_layout.closeDrawer(GravityCompat.START)
                        } else {
                            super.onBackPressed()
                        }                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, fragment)
                .addToBackStack(fragment.TAG)
                .commit()
    }

}
