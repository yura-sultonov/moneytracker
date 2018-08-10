package com.allerria.moneytracker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.common.BaseActivity
import com.allerria.moneytracker.ui.common.BaseActivityWithoutFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportAppNavigator

class LoadingActivity: BaseActivityWithoutFragment(), LoadingView {

    override val layoutRes = R.layout.activity_loading

    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter(): LoadingPresenter = LoadingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val welcomeThread = object : Thread() {

            override fun run() {
                try {
                    super.run()
                    Thread.sleep(2000)
                } catch (e: Exception) {

                } finally {
                    navigateToMainActivity()
                }
            }
        }
        welcomeThread.start()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}