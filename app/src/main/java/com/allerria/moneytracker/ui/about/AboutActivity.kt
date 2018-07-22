package com.allerria.moneytracker.ui.about

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
import com.allerria.moneytracker.ui.global.BaseActivity
import com.allerria.moneytracker.ui.main.MainActivity
import com.allerria.moneytracker.ui.settings.SettingsActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_about.*
import javax.inject.Inject


class AboutActivity : BaseActivity(), AboutView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var app: Context

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    @ProvidePresenter
    fun providePresenter(): AboutPresenter = AboutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)

        toolbar.title = getString(R.string.about)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(2).isChecked = true
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
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            R.id.nav_about -> {
                Log.d("TAG", "about")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}