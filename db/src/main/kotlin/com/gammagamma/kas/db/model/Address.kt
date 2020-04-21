package com.gammagamma.kas.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gammagamma.kas.db.entity.BaseEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "address"
)
@Parcelize
@JsonClass(generateAdapter = true)
data class Address(
    
    var street: String?,
    var suite: String?,
    var city: String?,
    
    @Json(name = "zipcode")
    var zipCode: String?

) : BaseEntity(), Parcelable
