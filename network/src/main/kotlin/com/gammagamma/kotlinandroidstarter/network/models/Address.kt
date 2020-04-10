package com.gammagamma.kotlinandroidstarter.network.models

import com.gammagamma.kotlinandroidstarter.domain.model.IAddress
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    
    override var street: String,
    override var suite: String,
    override var city: String,
    override var zipcode: String
    
) : IAddress
