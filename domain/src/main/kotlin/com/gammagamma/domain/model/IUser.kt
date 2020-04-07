package com.gammagamma.domain.model

import org.threeten.bp.OffsetDateTime

interface IUser {
    
    var id: Int
    
    var email: String
    
    var firstName: String
    var lastName: String
    var dob: OffsetDateTime
    var gender: Int
    
    var address: String
    var city: String
    var state: String
    var zipCode: String
    var country: String
    
    var phone: String
    
    //
    
    val fullName: String
    
}
