package com.gammagamma.kotlinandroidstarter.domain.model

import org.threeten.bp.OffsetDateTime

interface IUser {
    
    var id: Int
    
    var email: String
    
    var name: String
    //var dob: OffsetDateTime
    
    var address: IAddress
    
    var phone: String
    
    //
    
    val firstName: String
    val lastName: String
    
}
