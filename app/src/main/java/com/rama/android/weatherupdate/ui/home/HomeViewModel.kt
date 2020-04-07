package com.rama.android.weatherupdate.ui.home


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rama.android.weatherupdate.R
import com.rama.android.weatherupdate.model.CityData
import com.rama.android.weatherupdate.model.Data
import com.rama.android.weatherupdate.repository.MainRepository
import com.rama.android.weatherupdate.ui.base.BaseViewModel
import com.rama.android.weatherupdate.utils.log.Logger
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import com.rama.android.weatherupdate.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONArray
import java.io.*


class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val homePageRepository: MainRepository, val context: Context?
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val allHomeData = MutableLiveData<List<Data>>()
    val allCityData = MutableLiveData<Array<CityData>>()
    val loading: MutableLiveData<Boolean> = MutableLiveData()


    override fun onCreate() {
        onGetCityData(context)
    }

    private fun onGetCityData(context: Context?) {
        loading.postValue(true)
        val jsongString: String? = context?.let { readFromFile(it) }
        val cityTypeToken = object : TypeToken<Array<CityData>>() {}.type
        val cityList: Array<CityData> = Gson().fromJson(jsongString, cityTypeToken)
        allCityData.postValue(cityList)

    }

    private fun readFromFile(context: Context): String {
        val `is`: InputStream = context.resources.openRawResource(R.raw.city_list)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            var reader: Reader? = null
            try {
                reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            var n: Int = 0
            try {
                while (reader?.read(buffer).also({ n = it!! }) != -1) {
                    writer.write(buffer, 0, n)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val jsonString: String = writer.toString()
        Log.d("jsonString", jsonString)

        return jsonString
    }


    fun onGetHomePageData(selectedCities: String) {

        loading.postValue(true)
        compositeDisposable.addAll(
            homePageRepository.fetchHomePage(selectedCities)?.subscribeOn(schedulerProvider.io())
                ?.subscribe(
                    {
                        loading.postValue(false)
                        allHomeData.postValue(it)
                        Logger.e("the response", it.toString())
                    },
                    {
                        handleNetworkError(it)
                        loading.postValue(false)
                    }
                )
        )
    }
}