package com.gammagamma.kas.storage.model

import androidx.room.PrimaryKey
import com.gammagamma.kas.domain.model.IAddress
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    
    override var street: String,
    override var suite: String,
    override var city: String,
    override var zipcode: String
    
) : IAddress
