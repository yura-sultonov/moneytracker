package com.allerria.moneytracker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.allerria.moneytracker.R
import com.allerria.moneytracker.model.BalanceManager
import com.allerria.moneytracker.ui.about.AboutActivity
import com.allerria.moneytracker.ui.global.BaseActivity
import com.allerria.moneytracker.ui.settings.SettingsActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var app : Context

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var balanceManager: BalanceManager

    @ProvidePresenter
    fun providePresenter(): MainPresenter = MainPresenter(balanceManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.balance)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).isChecked = true
        presenter.showBalance()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_balance -> {
                Log.d("TAG", "balance")
            }
            R.id.nav_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showBalance(rubles: Double, dollars: Double) {
        rubles_text_view.text = "$rubles P"
        dollars_text_view.text = "$dollars $"
    }
}
