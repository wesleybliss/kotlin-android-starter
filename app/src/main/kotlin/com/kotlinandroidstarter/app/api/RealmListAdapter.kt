package com.kotlinandroidstarter.app.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import io.realm.RealmList
import java.io.IOException
import io.realm.RealmObject

/**
 * Derived from this example:
 * https://github.com/square/moshi/blob/master/moshi/src/main/java/com/squareup/moshi/CollectionJsonAdapter.java
 * https://github.com/Commit451/Regalia/
 */
class RealmListAdapter<T : RealmObject>(
    private val elementAdapter: JsonAdapter<T>)
    : JsonAdapter<RealmList<T>>() {
    
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: RealmList<T>?) {
        
        writer.beginArray()
        
        if (value != null)
            for (element in value)
                elementAdapter.toJson(writer, element)
        
        writer.endArray()
        
    }
    
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): RealmList<T>? {
        
        val result = RealmList<T>()
        
        reader.beginArray()
        
        while (reader.hasNext())
            result.add(elementAdapter.fromJson(reader))
        
        reader.endArray()
        
        return result
        
    }
    
}
