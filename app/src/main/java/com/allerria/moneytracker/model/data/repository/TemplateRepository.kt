package com.allerria.moneytracker.model.data.repository

import com.allerria.moneytracker.entity.Template
import com.allerria.moneytracker.model.data.datasource.local.AppDbHelper
import java.util.*
import javax.inject.Inject

class TemplateRepository @Inject constructor(private val db: AppDbHelper) {

    fun getTemplates() = db.wrapper.templateQueries.selectAll().executeAsList()

    fun updateEnabled(id: Long, enabled: Boolean) {
        db.wrapper.templateQueries.updateEnabled(enabled, id)
    }

    fun updateLastRun(id: Long, date: Calendar) {
        db.wrapper.templateQueries.updateLastRun(date, id)
    }

    fun deleteByWalletId(id: Long) {
        db.wrapper.templateQueries.deleteAllByWalletId(id)
    }

    fun deleteAll() {
        db.wrapper.templateQueries.deleteAll()
    }

    fun insertTemplate(template: Template) {
        db.wrapper.templateQueries.insertTemplate(template.type, template.enabled, template.transactionTypee, template.transactionCategory, template.transactionCurrency, template.transactionAmount, template.transactionWalletId, GregorianCalendar())
    }

}