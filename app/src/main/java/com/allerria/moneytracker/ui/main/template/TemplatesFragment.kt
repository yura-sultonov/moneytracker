package com.allerria.moneytracker.ui.main.template

import android.os.Bundle
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_templates.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TemplatesFragment : BaseFragment(), TemplatesView {
    override val layoutRes = R.layout.fragment_templates
    override val TAG = Screens.TEMPLATES_SCREEN

    @Inject
    lateinit var router: Router

    @Inject
    @InjectPresenter
    lateinit var presenter: TemplatesPresenter

    @ProvidePresenter
    fun providePresenter(): TemplatesPresenter {
        return  presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initTemplates()
        add_template_button.setOnClickListener {
            router.navigateTo(Screens.ADD_TEMPLATE_SCREEN)
        }
    }


}