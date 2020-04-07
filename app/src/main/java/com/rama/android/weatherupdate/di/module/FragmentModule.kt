package com.rama.android.weatherupdate.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rama.android.weatherupdate.repository.MainRepository
import com.rama.android.weatherupdate.ui.activities.CafeViewModel
import com.rama.android.weatherupdate.ui.home.HomeViewModel
import com.rama.android.weatherupdate.ui.home.ParentAdapter
import com.rama.android.weatherupdate.ui.base.BaseFragment
import com.rama.android.weatherupdate.ui.cafe.ParentCafeAdapter
import com.rama.android.weatherupdate.utils.ViewModelProviderFactory
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import com.rama.android.weatherupdate.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager =
        LinearLayoutManager(fragment.context, RecyclerView.VERTICAL, false)

    @Provides
    fun provideParentAdapter() = ParentAdapter(fragment.context, ArrayList())

    @Provides
    fun provideParentCafeAdapter() = ParentCafeAdapter(fragment.context, ArrayList())

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, homePageRepository: MainRepository
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(schedulerProvider, compositeDisposable, networkHelper, homePageRepository,fragment.context)
        }).get(HomeViewModel::class.java)

    @Provides
    fun provideCafeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper, cafePageRepository: MainRepository
    ): CafeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(CafeViewModel::class) {
            CafeViewModel(schedulerProvider, compositeDisposable, networkHelper, cafePageRepository)
        }).get(CafeViewModel::class.java)
}