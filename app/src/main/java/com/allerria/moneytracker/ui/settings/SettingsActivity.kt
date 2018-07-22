package com.allerria.moneytracker.ui.settings

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
import com.allerria.moneytracker.ui.about.AboutActivity
import com.allerria.moneytracker.ui.global.BaseActivity
import com.allerria.moneytracker.ui.main.MainActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject


class SettingsActivity : BaseActivity(), SettingsView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var app : Context

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter = SettingsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        toolbar.title = getString(R.string.settings)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(1).isChecked = true
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
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_settings -> {
                Log.d("TAG", "settings")
            }
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
