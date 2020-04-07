package com.rama.android.weatherupdate.ui.activities

import androidx.lifecycle.MutableLiveData
import com.rama.android.weatherupdate.model.Step2Data
import com.rama.android.weatherupdate.repository.MainRepository
import com.rama.android.weatherupdate.ui.base.BaseViewModel
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import com.rama.android.weatherupdate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class CafeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val cafePageRepository: MainRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val allCafeData = MutableLiveData<Step2Data>()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {

        onGetCafePageData()
    }

    fun onGetCafePageData(){

        loading.postValue(true)

        compositeDisposable.addAll(

            cafePageRepository.fetchCafePageList().subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        loading.postValue(false)
                        allCafeData.postValue(it)
                    },
                    {
                        handleNetworkError(it)
                        loading.postValue(false)
                    }
                )
        )
    }


}