package com.kotlinandroidstarter.app.viewmodels

import androidx.lifecycle.ViewModel
import com.kotlinandroidstarter.app.models.Thing
import com.kotlinandroidstarter.app.utils.RealmLiveData
import io.realm.Realm
import io.realm.kotlin.where

class FragBViewModel : ViewModel() {
    
    val things = RealmLiveData<Thing>(
        Realm.getDefaultInstance()
            .where<Thing>()
            .findAllAsync()
    )
    
    fun updateFilter(checked: Boolean) {
        things.setResults(
            with (
                Realm.getDefaultInstance().where<Thing>()) {
                if (!checked) findAllAsync()
                else greaterThan("id", 5).findAllAsync()
            }
        )
    }
    
}
