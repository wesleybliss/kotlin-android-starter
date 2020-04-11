package com.gammagamma.kas.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String
    
)