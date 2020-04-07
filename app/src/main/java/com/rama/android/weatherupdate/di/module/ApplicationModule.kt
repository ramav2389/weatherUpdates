package com.rama.android.weatherupdate.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.rama.android.weatherupdate.MainApplication
import com.rama.android.weatherupdate.BuildConfig
import com.rama.android.weatherupdate.data.remote.NetworkService
import com.rama.android.weatherupdate.data.remote.Network
import com.rama.android.weatherupdate.di.ApplicationContext
import com.rama.android.weatherupdate.utils.network.NetworkHelper
import com.rama.android.weatherupdate.utils.rx.RxSchedulerProvider
import com.rama.android.weatherupdate.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("newlist-project-prefs", Context.MODE_PRIVATE)

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
   /* @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "bootcamp-instagram-project-db"
        ).build()*/

    @Provides
    @Singleton
    fun provideNetworkService(networkHelper: NetworkHelper): NetworkService =
        Network.create(
            networkHelper,
            BuildConfig.BASE_URL,
            BuildConfig.TOKEN,
            BuildConfig.CONTENT_TYPE,
            BuildConfig.ACCEPT,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}