package com.kotlinandroidstarter.app.utils

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

class RealmLiveData<T : RealmModel>(private var realmResults: RealmResults<T>) : LiveData<RealmResults<T>>() {
    
    private var listener = RealmChangeListener<RealmResults<T>> {
        value = it
    }
    
    override fun onActive() {
        realmResults.addChangeListener(listener)
    }
    
    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }
    
    fun setResults(newRealmResults: RealmResults<T>) {
        
        realmResults.removeChangeListener(listener)
        
        realmResults = newRealmResults
        
        if (hasActiveObservers())
            realmResults.addChangeListener(listener)
        
    }
    
}

fun <T : RealmModel> RealmResults<T>.asLiveData() = RealmLiveData(this)
