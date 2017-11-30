package com.kotlinandroidstarter.app.di.utils

import android.support.v4.app.Fragment
import com.kotlinandroidstarter.app.di.ui.shared.BaseFragmentModule
import com.kotlinandroidstarter.app.di.scopes.PerFragment
import javax.inject.Inject
import javax.inject.Named




/**
 * A class that does something.
 * <p>
 * This class has the {@link PerFragment} scope. This means that the Fragment and all of its child
 * fragments and their dependencies will share the same instance of this class. However, different
 * fragment instances will have their own instances.
 * <p>
 * This is not available at the Activity and Application.
 */
@PerFragment
class PerFragmentUtil @Inject constructor(@Named(BaseFragmentModule.FRAGMENT) private val fragment: Fragment) {
    
    /**
     * @return the result of the work done here as a string. For this example, this returns its
     * [.hashCode] and the fragment's [.hashCode].
     */
    fun doSomething(): String =
        "PerFragmentUtil: " + hashCode() + ", Fragment: " + fragment.hashCode()
    
}