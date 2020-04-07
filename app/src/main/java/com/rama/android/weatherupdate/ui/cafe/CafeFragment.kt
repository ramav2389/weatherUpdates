package com.rama.android.weatherupdate.ui.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rama.android.weatherupdate.R
import com.rama.android.weatherupdate.di.component.FragmentComponent
import com.rama.android.weatherupdate.model.Step2List
import com.rama.android.weatherupdate.ui.base.BaseFragment
import com.rama.android.weatherupdate.ui.cafe.ParentCafeAdapter
import kotlinx.android.synthetic.main.fragment_step2.*
import javax.inject.Inject

class CafeFragment : BaseFragment<CafeViewModel>() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var parentCafeAdapter: ParentCafeAdapter


    companion object {

        const val TAG = "CafeFragment"

        fun newInstance(): CafeFragment {
            val args = Bundle()
            val fragment = CafeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_step2

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {

        super.setupObservers()

        viewModel.allCafeData.observe(this, Observer {


            val resultList: ArrayList<Step2List> = ArrayList()

            resultList.run {
                add(0, Step2List(it.step2.banner,ArrayList(),it.step2.description))
            }

            resultList.add(Step2List("", it.step2.images,""))

            parentCafeAdapter.appendData(resultList)
        })
    }


    override fun setupView(view: View) {
        rv_cafe_parent.layoutManager = linearLayoutManager
        rv_cafe_parent.adapter = parentCafeAdapter
    }

}