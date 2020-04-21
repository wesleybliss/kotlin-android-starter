package com.gammagamma.kas.domain.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    
    var id: Long,
    
    var street: String?,
    var suite: String?,
    var city: String?,
    
    @Json(name = "zipcode")
    var zipCode: String?

)
