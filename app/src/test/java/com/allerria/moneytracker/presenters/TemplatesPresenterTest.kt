package com.allerria.moneytracker.presenters

import com.allerria.moneytracker.model.data.repository.TemplateRepository
import com.allerria.moneytracker.ui.main.template.TemplatesPresenter
import com.allerria.moneytracker.ui.main.template.TemplatesView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import javax.xml.transform.Templates

@RunWith(JUnit4::class)
class TemplatesPresenterTest {

    private lateinit var templatesPresenter: TemplatesPresenter
    private lateinit var templatesRepository: TemplateRepository
    private lateinit var templatesView: TemplatesView

    @Before
    fun init() {
        templatesRepository = mock(TemplateRepository::class.java)
        templatesView = mock(TemplatesView::class.java)
        templatesPresenter = TemplatesPresenter(templatesRepository)
        templatesPresenter.attachView(templatesView)
    }

    @Test
    fun testInitView() {
        doNothing().`when`(templatesView).setTemplates(listOf())
        doReturn(listOf<Templates>()).`when`(templatesRepository).getTemplates()
        templatesPresenter.initTemplates()
        verify(templatesRepository).getTemplates()
        verify(templatesView).setTemplates(listOf())
        verifyNoMoreInteractions(templatesRepository)
        verifyNoMoreInteractions(templatesView)
    }
}