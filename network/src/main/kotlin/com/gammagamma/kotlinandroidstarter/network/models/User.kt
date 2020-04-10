package com.gammagamma.kotlinandroidstarter.network.models

import com.gammagamma.kotlinandroidstarter.domain.model.IAddress
import com.gammagamma.kotlinandroidstarter.domain.model.IUser
import com.squareup.moshi.JsonClass
import org.threeten.bp.OffsetDateTime

@JsonClass(generateAdapter = true)
data class User(
    
    override var id: Int,
    
    override var email: String,
    
    override var name: String,
    //override var dob: OffsetDateTime,
    
    override var address: Address,
    
    override var phone: String

) : IUser {
    
    override val firstName get() = getNamePart(0)
    override val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { name.split(" ")[part] } catch (e: Exception) { "" }
    
}
