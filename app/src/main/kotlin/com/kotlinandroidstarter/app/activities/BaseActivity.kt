package com.kotlinandroidstarter.app.activities

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


open class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    
    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    
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
    
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
    
}