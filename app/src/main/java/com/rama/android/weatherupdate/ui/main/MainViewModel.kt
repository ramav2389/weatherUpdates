package com.rama.android.weatherupdate.ui.main

import androidx.lifecycle.MutableLiveData
import com.rama.android.weatherupdate.ui.base.BaseViewModel
import com.rama.android.weatherupdate.utils.common.Event
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import com.rama.android.weatherupdate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val cafeNavigation = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        homeNavigation.postValue(Event(true))
    }

    fun onHomeSelected() {
        homeNavigation.postValue(Event(true))
    }

    fun onCafeSelected() {
        cafeNavigation.postValue(Event(true))
    }

}
