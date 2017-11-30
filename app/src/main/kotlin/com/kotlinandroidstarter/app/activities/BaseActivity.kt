package com.kotlinandroidstarter.app.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


open class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    
    /*@Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    lateinit var fragmentManager: FragmentManager*/
    
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
    
    protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment)
            .commit()
    }
    
}