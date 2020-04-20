package com.gammagamma.kas.network.adapter

import com.gammagamma.kas.domain.db.AddressId
import com.gammagamma.kas.domain.db.UserId
import com.gammagamma.kas.logging.plankE
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

@Suppress("unused")
class AddressIdAdapter {
    
    @FromJson
    fun fromJson(value: Long): AddressId? {
        
        return try {
            AddressId(value)
        } catch (e: Exception) {
            plankE(e, "Failed to convert AddressId")
            null
        }
        
    }
    
    @ToJson
    fun toJson(userId: AddressId): Long? = try {
        userId.value
    } catch (e: Exception) {
        plankE(e, "Failed to convert AddressId to Long")
        null
    }
    
}
