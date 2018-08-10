package com.allerria.moneytracker.ui.main.template

import com.allerria.moneytracker.model.data.repository.TemplateRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TemplatesPresenter @Inject constructor(private val templateRepository: TemplateRepository) : MvpPresenter<TemplatesView>() {
    fun initTemplates() {
        viewState.setTemplates(templateRepository.getTemplates())
    }

}