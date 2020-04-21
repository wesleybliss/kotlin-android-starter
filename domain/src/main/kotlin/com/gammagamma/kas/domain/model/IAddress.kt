package com.gammagamma.kas.domain.model

interface IAddress {
    
    var id: Long
    
    var street: String?
    var suite: String?
    var city: String?
        
    var zipCode: String?
    
}
