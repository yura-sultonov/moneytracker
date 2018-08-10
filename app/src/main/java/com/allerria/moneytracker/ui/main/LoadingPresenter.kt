package com.allerria.moneytracker.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class LoadingPresenter : MvpPresenter<LoadingView>() {

    fun syncHistory() {
//        val templates = templateRepository.getTemplates()
//        for (template in templates) {
//            // if (template.enabled && Calendar.getInstance().time. - template.lastRunDate.time)
//        }
        viewState.navigateToMainActivity()
    }
}