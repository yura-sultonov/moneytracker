package com.allerria.moneytracker.ui.global

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment


abstract class BaseFragment: MvpAppCompatFragment() {
    protected abstract val layoutRes: Int
    abstract val TAG: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutRes, container, false)
}