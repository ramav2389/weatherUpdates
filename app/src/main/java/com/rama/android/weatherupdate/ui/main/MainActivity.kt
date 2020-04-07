package com.rama.android.weatherupdate.ui.main

import com.rama.android.weatherupdate.di.component.ActivityComponent
import com.rama.android.weatherupdate.ui.base.BaseActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rama.android.weatherupdate.ui.activities.CafeFragment
import com.rama.android.weatherupdate.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel>() {

    private var activeFragment: Fragment? = null

    companion object {
        const val TAG = "MainActivity"
    }

    override fun provideLayoutId(): Int = com.rama.android.weatherupdate.R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


    override fun setupView(savedInstanceState: Bundle?) {
        bottomNavigation.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    com.rama.android.weatherupdate.R.id.itemHome -> {
                        viewModel.onHomeSelected()
                        true
                    }
                    com.rama.android.weatherupdate.R.id.itemDSCCafe -> {
                        viewModel.onCafeSelected()
                        true
                    }
                    else -> false
                }
            }
        }
    }


    override fun setupObservers() {

        super.setupObservers()

        viewModel.homeNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showStep1() }
        })

        viewModel.cafeNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showStep2() }
        })
    }

    override fun onBackPressed() {
        if ((bottomNavigation.selectedItemId == com.rama.android.weatherupdate.R.id.itemHome)) {
            showInnerHomeFragment(false)
        } else
            super.onBackPressed()
    }

    fun showInnerHomeFragment(isShowing: Boolean) {
        showStep1()
    }

    private fun showStep1() {

        //on Click of Home in bottom Navigation view while on home if already Home Fragment is in foreground then return
        if (activeFragment is HomeFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment =
            supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment?
        if (fragment == null) {

            fragment = HomeFragment.newInstance()
            fragmentTransaction.add(
                com.rama.android.weatherupdate.R.id.container,
                fragment,
                HomeFragment.TAG
            )

        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }


    private fun showStep2() {
        if (activeFragment is CafeFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(CafeFragment.TAG) as CafeFragment?

        if (fragment == null) {
            fragment = CafeFragment.newInstance()
            fragmentTransaction.add(
                com.rama.android.weatherupdate.R.id.container,
                fragment,
                CafeFragment.TAG
            )
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }
}


