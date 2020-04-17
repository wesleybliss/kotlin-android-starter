package com.gammagamma.kas.storage.model

import androidx.room.Entity
import com.gammagamma.kas.domain.model.IUser
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
@Entity
data class User(
    
    override var id: Long,
    
    override var email: String,
    
    override var name: String,
    //override var dob: OffsetDateTime,
    
    override var address: Address,
    
    override var phone: String

) : IUser {
    
    val firstName get() = getNamePart(0)
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { name.split(" ")[part] } catch (e: Exception) { "" }
    
}
