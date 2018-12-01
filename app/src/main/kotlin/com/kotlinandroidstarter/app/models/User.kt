package com.kotlinandroidstarter.app.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    
    @PrimaryKey
    var id: Int? = null,
    
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: Address? = null,
    var phone: String? = null,
    var website: String? = null,
    var company: Company? = null
    
) : RealmObject()

open class Geo(
    var lat: Double? = null,
    var lng: Double? = null
) : RealmObject()

open class Address(
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: Geo? = null
) : RealmObject()

open class Company(
    var name: String? = null,
    var catchPhrase: String? = null,
    var bs: String? = null
) : RealmObject()
