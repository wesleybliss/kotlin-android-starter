package com.kotlinandroidstarter.app.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Thing(
    
    @PrimaryKey
    var id: Int? = null,
    
    var name: String? = ""
    
) : RealmObject()
