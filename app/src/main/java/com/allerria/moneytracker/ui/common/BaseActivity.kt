package com.allerria.moneytracker.ui.common

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

}