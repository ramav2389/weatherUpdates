package com.rama.android.weatherupdate.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.R
import com.rama.android.weatherupdate.di.component.FragmentComponent
import com.rama.android.weatherupdate.model.CityData
import com.rama.android.weatherupdate.model.HomeActivity
import com.rama.android.weatherupdate.ui.base.BaseFragment
import com.rama.android.weatherupdate.ui.interfaces.IViewTapCallback
import com.rama.android.weatherupdate.utils.common.Toaster
import com.rama.android.weatherupdate.utils.log.Logger
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : IViewTapCallback, BaseFragment<HomeViewModel>() {

    companion object {

        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var recyclerView: RecyclerView
    lateinit var request: Button

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var parentAdapter: ParentAdapter

    var cityList: List<CityData> = ArrayList()
    var selectedCityList: MutableList<Int> = ArrayList()
    var resultList: List<HomeActivity> = ArrayList()

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.allHomeData.observe(this, Observer {
            Logger.d("List", it.toString())
        })

        viewModel.allCityData.observe(this, Observer {
            Logger.d("List", it.toString())
            cityList = it.toList()
            recyclerView = rv_parent
            recyclerView.apply {
                layoutManager = linearLayoutManager
                parentAdapter.addData(cityList, false)
                adapter = parentAdapter
            }
        })

    }

    override fun setupView(view: View) {
        parentAdapter.setViewTapListener(this)
        request = bt_click
        request.setOnClickListener(View.OnClickListener {
            context?.let { it1 -> Toaster.show(it1, "sele" + selectedCityList.toString()) }
            viewModel.onGetHomePageData(android.text.TextUtils.join(",", selectedCityList))
        })
    }

    override fun onViewTapped(tag: Any?, viewHolder: Any?) {
        selectedCityList.addAll(tag as List<Int>)
    }

}