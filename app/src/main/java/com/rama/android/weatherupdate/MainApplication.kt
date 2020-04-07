package com.rama.android.weatherupdate

import android.app.Application
import com.rama.android.weatherupdate.di.component.ApplicationComponent
import com.rama.android.weatherupdate.di.component.DaggerApplicationComponent
import com.rama.android.weatherupdate.di.module.ApplicationModule

class MainApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()

    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}