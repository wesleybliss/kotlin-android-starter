package com.gammagamma.kas.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import com.gammagamma.kas.db.entity.BaseEntity
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Suppress("unused")
@Entity(
    tableName = "user",
    indices = [
        Index(value = ["email"])
    ]
)
@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    
    var email: String,
    
    var name: String?,
    //var dob: OffsetDateTime,
    
    var address: Address?,
    
    var phone: String?

) : BaseEntity(), Parcelable {
    
    @get:Ignore
    @IgnoredOnParcel
    val firstName get() = getNamePart(0)
    
    @get:Ignore
    @IgnoredOnParcel
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { (name ?: "").split(" ")[part] }
        catch (e: Exception) { "" }
    
}
