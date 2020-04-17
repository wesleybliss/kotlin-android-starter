package com.gammagamma.kas.domain.model

import com.gammagamma.kas.db.data.Address
import com.gammagamma.kas.db.data.User
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class User(
    
    override var id: Long,

    override var email: String,

    override var name: String,
    //var dob: OffsetDateTime,

    override var address: Address,

    override var phone: String

) : User {
    
    val firstName get() = getNamePart(0)
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { name.split(" ")[part] } catch (e: Exception) { "" }
    
}

