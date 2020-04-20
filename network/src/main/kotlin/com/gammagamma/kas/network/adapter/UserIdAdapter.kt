package com.gammagamma.kas.network.adapter

import com.gammagamma.kas.domain.db.UserId
import com.gammagamma.kas.logging.plankE
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

@Suppress("unused")
class UserIdAdapter {
    
    @FromJson
    fun fromJson(value: Long): UserId? {
        
        return try {
            UserId(value)
        } catch (e: Exception) {
            plankE(e, "Failed to convert UserId")
            null
        }
        
    }
    
    @ToJson
    fun toJson(userId: UserId): Long? = try {
        userId.value
    } catch (e: Exception) {
        plankE(e, "Failed to convert UserId to Long")
        null
    }
    
}
