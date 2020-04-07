package com.rama.android.weatherupdate.di.component

import com.rama.android.weatherupdate.di.ActivityScope
import com.rama.android.weatherupdate.di.module.ActivityModule
import com.rama.android.weatherupdate.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}