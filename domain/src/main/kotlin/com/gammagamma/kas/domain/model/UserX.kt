package com.gammagamma.kas.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserX(
    
    var id: Int,
    
    var email: String,
    
    var name: String,
    //var dob: OffsetDateTime,
    
    var address: Address,
    
    var phone: String

) {
    
    val firstName get() = getNamePart(0)
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { name.split(" ")[part] } catch (e: Exception) { "" }
    
}
