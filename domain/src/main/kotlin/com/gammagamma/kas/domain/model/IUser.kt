package com.gammagamma.kas.domain.model

interface IUser {
    
    var id: Int
    
    var email: String
    
    var name: String?
    //var dob: OffsetDateTime
    
    var address: IAddress?
    
    var phone: String?
    
}
