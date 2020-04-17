package com.gammagamma.kas.domain.model

import com.gammagamma.kas.db.data.Address
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    
    override val id: Long,
    override var street: String,
    override var suite: String,
    override var city: String,
    override var zipcode: String

) : Address
