package com.allerria.moneytracker.ui.global

import android.content.Context
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}