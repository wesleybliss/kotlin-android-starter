package com.gammagamma.kotlinandroidstarter.net.adapter

import android.util.Log
import com.gammagamma.logging.plank
import com.gammagamma.logging.plankE
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class DateTimeAdapter {
    
    @FromJson
    fun fromJson(date: String): OffsetDateTime? {
        
        plank("DateTimeAdapter called")
    
        return try {
            OffsetDateTime.parse(date) //.atZoneSameInstant(ZoneId.systemDefault())
        } catch (e: Exception) {
            plankE(e, "Failed to parse date")
            null
        }
    }
    
    @ToJson
    fun toJson(date: OffsetDateTime) = try {
        date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    } catch (e: Exception) {
        plankE(e, "Failed to format date to string")
        null
    }
    
}
