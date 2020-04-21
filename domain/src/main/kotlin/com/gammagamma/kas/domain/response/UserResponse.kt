package com.gammagamma.kas.domain.response

import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class UserResponse(
    
    var id: Long,
    
    var email: String,
    
    var name: String?,
    //var dob: OffsetDateTime,
    
    var address: AddressResponse?,
    
    var phone: String?

)
