package com.gammagamma.kas.domain.response

import com.gammagamma.kas.domain.net.NetworkResponseObject
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    
    val id: Long,
    
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipCode: String?,
    val country: String?

) : NetworkResponseObject
