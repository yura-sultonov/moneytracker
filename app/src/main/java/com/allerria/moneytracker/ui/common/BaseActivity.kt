package com.allerria.moneytracker.ui.common

import android.os.Bundle
import com.allerria.moneytracker.MoneyTrackerApp
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    protected abstract val layoutRes: Int
    protected abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}