package com.allerria.moneytracker.ui.main.template

import com.allerria.moneytracker.Templates
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface TemplatesView : MvpView {
    fun setTemplates(templates: List<Templates>)
}