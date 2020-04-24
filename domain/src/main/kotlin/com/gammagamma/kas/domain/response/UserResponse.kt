package com.gammagamma.kas.domain.response

import com.gammagamma.kas.domain.net.NetworkResponseObject
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class UserResponse(
    
    val id: Long,
    
    val email: String,
    
    val name: String?,
    //val dob: OffsetDateTime,
    
    val address: AddressResponse?,
    
    val phone: String?

) : NetworkResponseObject
