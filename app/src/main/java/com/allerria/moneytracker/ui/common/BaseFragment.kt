package com.allerria.moneytracker.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


abstract class BaseFragment : MvpAppCompatFragment(), HasSupportFragmentInjector {
    protected abstract val layoutRes: Int
    abstract val TAG: String

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { restoreState(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutRes, container, false)

    protected open fun restoreState(state: Bundle) {}
}