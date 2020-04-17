package com.gammagamma.kas.domain.model

interface IAddress {
    
    val id: Int
    
    var street: String
    var suite: String
    var city: String
    var zipcode: String
    
}
