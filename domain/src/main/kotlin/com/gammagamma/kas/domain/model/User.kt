package com.gammagamma.kas.domain.model

import com.gammagamma.kas.domain.db.UserId
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class User(
    
    var id: UserId?,
    
    var email: String?,
    
    var name: String?,
    //var dob: OffsetDateTime,
    
    var address: Address,
    
    var phone: String?

) {
    
    val firstName get() = getNamePart(0)
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { (name ?: "").split(" ")[part] }
        catch (e: Exception) { "" }
    
}

