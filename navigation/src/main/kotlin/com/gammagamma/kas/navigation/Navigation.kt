package com.gammagamma.kas.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.gammagamma.kas.logging.plank
import com.gammagamma.kas.logging.plankE
import com.gammagamma.kas.logging.plankW
import com.gammagamma.kas.ui.extensions.addExtras
import org.koin.core.KoinComponent
import org.koin.core.inject
import javax.inject.Provider

/**
 * Global navigation using only intents, so modules can be fully
 * decoupled & not depend on each other for launching new activities
 */
@Suppress("unused")
object Navigation : KoinComponent {
    
    //region Properties
    
    // @todo Might be able to remove this if we use instrumentation instead of unit tests for this class
    // Optional custom map override - mostly for tests
    private var customMap: Map<String, String>? = null
    
    // Primary, global context object, provided by Koin
    private val context: Context by inject()
    
    // Primary package name for all modules
    private val packageNamespace by lazy { BuildConfig.PACKAGE_NAMESPACE }
    
    // Root package name, evaluated from the primary context
    // Note: this *changes* depending on the type of build, & therefore can't be hard-coded
    private val packageName by lazy { context.applicationContext.packageName }
    
    // Routing table for matching a path (e.g. "MainActivity") with a namespace (e.g. "com.foo.bar.MainActivity")
    private val routes: Map<String, String> by lazy {
        if (customMap != null) customMap!!
        else createRoutingMap() ?: throw RuntimeException("Failed to parse activities from package")
    }
    
    private val topActivityProvider: Provider<Activity> by inject()
    
    private const val ACTIVITY_SPLASH = "SplashActivity"
    private const val ACTIVITY_MAIN = "MainActivity"
    private const val ACTIVITY_SETTINGS = "SettingsActivity"
    
    
    //endregion Properties
    
    //region Override Helpers
    
    /**
     * Helper for initializing a custom routing table.
     * Since this is a singleton object class, in some cases (e.g. tests),
     * it might be necessary to call this before having Koin inject
     */
    fun initWithCustomMap(customRoutes: Map<String, String>) {
        
        if (customMap?.isEmpty() == true)
            throw RuntimeException("No routes given for custom routing map")
        
        customMap = customRoutes
        
        plank("Initialized with custom routes")
        
    }
    
    fun foo(value: String) = value.loadIntentOrNull()
    
    //endregion Override Helpers
    
    //region Routing Map
    
    /**
     * Returns the routing table [Map] for inspection
     */
    fun getRoutingMap() = routes
    
    /**
     * Builds a dynamic routing table [Map] based on the activities in this app.
     * These can be used to launch in-app deep links
     *
     * @sample
     * SplashActivity --> com.foo.bar.ui.SplashActivity
     * LoginActivity --> com.foo.bar.auth.LoginActivity
     */
    private fun createRoutingMap() : Map<String, String>? = try {
        
        val packageManager = context.applicationContext.packageManager
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        
        plank("Looking for automatic routes based on $packageName (${packageInfo.activities.size} activities)")
        
        val paths: MutableMap<String, String> = packageInfo.activities.fold(mutableMapOf()) { acc, it ->
            
            plank("Evaluating activity ${it.name}")
            
            val prefix = it.name.substring(0, packageNamespace.length)
            
            if (prefix != packageNamespace) {
                plankW(it.name, "doesn't start with", packageNamespace)
                return@fold acc
            }
            
            if (!it.name.endsWith("Activity")) {
                plankW(it.name, "doesn't end with Activity")
                return@fold acc
            }
            
            val fileName = it.name.split(".").last()
            plank("Route: $fileName -> ${it.name}")
            acc.apply { put(fileName, it.name) }
            
        }
        
        plank("NAVIGATION ROUTING TABLE")
        
        paths.forEach { (route, namespace) ->
            plank("$route --> $namespace")
        }
        
        paths
        
    } catch (e: Exception) {
        
        plankE(e, "Failed to parse activities from package")
        
        null
        
    }
    
    private fun <K, V> dumpRoutes(item: Map<K, V>) =
        item.keys.joinToString("\n") { "$it = ${item[it]}" }
    
    //endregion Routing Map
    
    //region Intent Creation
    
    /**
     * Validates a request to launch an [Activity], where [path] is
     * the activity name (e.g. "MainActivity"), and [newTask] & [clearTask]
     * are optional overrides (see [Intent])
     *
     * @throws RuntimeException if it can't find the intent namespace for [path]
     * @return [Intent]
     */
    private fun validateLaunchRequest(
        path: String,
        newTask: Boolean = false,
        clearTask: Boolean = false
    ) : Intent {
        
        if (!path.endsWith("Activity"))
            throw RuntimeException("Path must end with \"Activity\", e.g. \"MainActivity\"")
        
        val route = routes[path] ?: throw RuntimeException("Invalid path - $path not found in routing table " + dumpRoutes(routes))
        val intent = route.loadIntentOrNull() ?: throw RuntimeException("Failed to create intent for $path / $route")
        
        intent.apply {
            setClassName(packageName, route)
            if (newTask) newTask()
            if (clearTask) clearTask()
        }
        
        return intent
        
    }
    
    /**
     * Launches an activity, where [path] is the activity name (e.g. "MainActivity"),
     *
     * [bundle] is an optional [Bundle] for the [Intent]
     * [options] is an optional [ActivityOptionsCompat] object (e.g. for shared view transitions)
     * [newTask] & [clearTask] are optional flags for the [Intent]
     *
     * @throws RuntimeException if the [Intent] namespace can't be created from [path]
     */
    @PreferMethod(
        "Preferably use the specific method for an activity",
        "E.g. for MainActivity, use launchMainActivity(...)")
    fun Context.launch(
        path: String,
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false,
        bundle: Bundle? = null
    ) {
        
        // Validate the requested activity & create an Intent
        val intent = validateLaunchRequest(path, newTask, clearTask)
        
        // Apply bundle data to intent, if applicable
        if (bundle != null) intent.putExtras(bundle)
        
        plank("Launching activity $path, ${routes[path]}")
        
        if (options == null) startActivity(intent)
        else startActivity(intent, options.toBundle())
        
    }
    
    fun Context.launch(
        path: String,
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false,
        vararg extras: Pair<String, Any?>?
    ) = launch(
        path,
        options,
        newTask,
        clearTask,
        Bundle().addExtras(*extras)
    )
    
    /**
     * Helper method for cases where we do have direct access to an [Activity],
     * we can simply call [launch] directly from that class.
     *
     * Generally we want to avoid tightly coupling activities,
     * so this should be used for edge cases only
     *
     * [options] is an optional [ActivityOptionsCompat] object (e.g. for shared view transitions)
     * [newTask] & [clearTask] are optional flags for the [Intent]
     * [extras] for creating an intent bundle
     *
     * @throws RuntimeException if [Intent] fails
     *
     * @sample
     * MainActivity.launch(...)
     */
    @PreferMethod(
        "Preferably use the specific method for an activity",
        "E.g. for MainActivity, use launchMainActivity(...)")
    inline fun <reified T : Activity> Context.launch(
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false,
        vararg extras: Pair<String, Any?>?
    ) {
        
        try {
            
            val activityName = T::class.java.toString()
                .split("class ").last().trim()
            
            launch(activityName, options, newTask, clearTask, *extras)
            
        } catch (e: Exception) {
            
            throw RuntimeException("Failed to derive path from activity name", e)
            
        }
        
    }
    
    //endregion Intent Creation
    
    //region Convenience Methods
    
    fun Context.launchSplashActivity(
        vararg extras: Pair<String, Any?>?,
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false) =
        launch(ACTIVITY_SPLASH, options, newTask, clearTask, *extras)
    
    fun Context.launchMainActivity(
        vararg extras: Pair<String, Any?>?,
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false) =
        launch(ACTIVITY_MAIN, options, newTask, clearTask, *extras)
    
    fun Context.launchSettingsActivity(
        vararg extras: Pair<String, Any?>?,
        options: ActivityOptionsCompat? = null,
        newTask: Boolean = false,
        clearTask: Boolean = false) =
        launch(ACTIVITY_SETTINGS, options, newTask, clearTask, *extras)
    
    //endregion Convenience Methods
    
}
