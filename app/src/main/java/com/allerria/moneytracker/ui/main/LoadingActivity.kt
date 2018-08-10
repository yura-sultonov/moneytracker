package com.allerria.moneytracker.ui.main

import android.content.Intent
import android.os.Bundle
import com.allerria.moneytracker.R
import com.allerria.moneytracker.ui.common.BaseActivityWithoutFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

class LoadingActivity : BaseActivityWithoutFragment(), LoadingView {

    override val layoutRes = R.layout.activity_loading

    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter(): LoadingPresenter = LoadingPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //presenter.syncHistory()
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

    override fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}