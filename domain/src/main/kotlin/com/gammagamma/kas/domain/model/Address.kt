package com.gammagamma.kas.domain.model

import com.gammagamma.kas.domain.db.AddressId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    
    var id: AddressId?,
    
    var street: String?,
    var suite: String?,
    var city: String?,
    
    @Json(name = "zipcode")
    var zipCode: String?

)
