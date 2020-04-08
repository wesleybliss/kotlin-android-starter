package com.gammagamma.kotlinandroidstarter.network.models

import com.gammagamma.domain.model.IUser
import com.squareup.moshi.JsonClass
import org.threeten.bp.OffsetDateTime

@JsonClass(generateAdapter = true)
data class User(
    
    override var id: Int,
    
    override var email: String,
    
    override var firstName: String,
    override var lastName: String,
    override var dob: OffsetDateTime,
    override var gender: Int,
    
    override var address: String,
    override var city: String,
    override var state: String,
    override var zipCode: String,
    override var country: String,
    
    override var phone: String

) : IUser {
    
    override val fullName get() = "$firstName $lastName"
    
}
