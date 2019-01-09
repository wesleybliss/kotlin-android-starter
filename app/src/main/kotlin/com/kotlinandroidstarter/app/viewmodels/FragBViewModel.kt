package com.kotlinandroidstarter.app.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.kotlinandroidstarter.app.App
import com.kotlinandroidstarter.app.models.Thing
import com.kotlinandroidstarter.extensions.mutableLiveDataOf
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.kotlin.where

class FragBViewModel : ViewModel() {
    
    val things = mutableLiveDataOf(listOf<Thing>())
    
    private var results = Realm.getDefaultInstance().where<Thing>().findAllAsync()
    private val listener = RealmChangeListener<RealmResults<Thing>> {
        things.value = it.toList()
    }
    
    private fun setup() {
        results.addChangeListener(listener)
        things.value = results.toList()
    }
    
    init {
        setup()
    }
    
    override fun onCleared() {
        results.removeChangeListener(listener)
        super.onCleared()
    }
    
    fun updateFilter(checked: Boolean) {
        
        results.removeChangeListener(listener)
        
        results = if (checked)
            /*Realm.getDefaultInstance().where<Thing>()
                .greaterThan("id", 5).findAllAsync()*/
        else
            Realm.getDefaultInstance().where<Thing>().findAllAsync()
        
        setup()
        
    }
    
}
