package com.kotlinandroidstarter.app.fragments

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseFragment : Fragment(), HasSupportFragmentInjector {
    
    @Inject
    protected lateinit var activityContext: Context
    
    // Note that this should not be used within a child fragment.
    /*@Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected var childFragmentManager: FragmentManager? = null*/
    
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    
    override fun onAttach(context: Context?) {
        // This is called even for API levels below 23.
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector
    
    protected fun addChildFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment)
            .commit()
    }
    
}