package com.allerria.moneytracker.ui.main.template

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.allerria.moneytracker.R
import com.allerria.moneytracker.Screens
import com.allerria.moneytracker.Templates
import com.allerria.moneytracker.ui.common.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_templates.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TemplatesFragment : BaseFragment(), TemplatesView {

    override val layoutRes = R.layout.fragment_templates
    override val TAG = Screens.TEMPLATES_SCREEN

    lateinit var localTemplates: List<Templates>
    private val templatesAdapter by lazy { TemplatesAdapter() }

    @Inject
    lateinit var router: Router

    @Inject
    @InjectPresenter
    lateinit var presenter: TemplatesPresenter

    @ProvidePresenter
    fun providePresenter(): TemplatesPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initTemplates()
        add_template_button.setOnClickListener {
            router.navigateTo(Screens.ADD_TEMPLATE_SCREEN)
        }
        with(templates_recycler_view) {
            adapter = templatesAdapter
            setHasFixedSize(true)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)

                    val position = parent.getChildAdapterPosition(view)
                    val last = parent.adapter!!.itemCount - 1

                    outRect.top = resources.getDimension(R.dimen.margin_8).toInt()
                    outRect.left = resources.getDimension(R.dimen.margin_16).toInt()
                    outRect.right = resources.getDimension(R.dimen.margin_16).toInt()

                    if (position == 0) {
                        outRect.top = resources.getDimension(R.dimen.margin_20).toInt()
                    }

                    if (position == last) {
                        outRect.bottom = resources.getDimension(R.dimen.margin_20).toInt()
                    }
                }
            })
        }
    }

    override fun setTemplates(templates: List<Templates>) {
        localTemplates = templates
        templatesAdapter.setData(localTemplates)
    }


}
