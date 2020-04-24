package com.gammagamma.kas.domain.model

import com.gammagamma.kas.sqldelight.data.User

/*@Suppress("unused")
@JsonClass(generateAdapter = true)
data class User(
    
    var id: UserId?,
    
    var email: String?,
    
    var name: String?,
    //var dob: OffsetDateTime,
    
    var address: Address?,
    
    var phone: String?

) {
    
    val firstName get() = getNamePart(0)
    val lastName get() = getNamePart(1)
    
    private fun getNamePart(part: Int) =
        try { (name ?: "").split(" ")[part] }
        catch (e: Exception) { "" }
    
}*/

val User.firstName get() = getNamePart(0)
val User.lastName get() = getNamePart(1)

private fun User.getNamePart(part: Int) =
    try { (name ?: "").split(" ")[part] }
    catch (e: Exception) { "" }
