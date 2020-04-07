package com.rama.android.weatherupdate.di.component

import com.rama.android.weatherupdate.ui.activities.CafeFragment
import com.rama.android.weatherupdate.di.FragmentScope
import com.rama.android.weatherupdate.di.module.FragmentModule
import com.rama.android.weatherupdate.ui.home.HomeFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: CafeFragment)
}