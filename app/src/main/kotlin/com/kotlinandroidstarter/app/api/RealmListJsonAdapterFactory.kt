package com.kotlinandroidstarter.app.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Enables Moshi to parse and serialize [io.realm.RealmList]s. Install via your [com.squareup.moshi.Moshi.Builder]
 * https://github.com/Commit451/Regalia/
 */
class RealmListJsonAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {

        val rawType = Types.getRawType(type)

        if (!annotations.isEmpty()) return null
        if (rawType != RealmList::class.java) return null

        return newRealmListAdapter<RealmObject>(type, moshi).nullSafe()

    }

    private fun <T : RealmObject> newRealmListAdapter(type: Type, moshi: Moshi): JsonAdapter<RealmList<T>> {

        val elementType = Types.collectionElementType(type, RealmList::class.java)
        val elementAdapter = moshi.adapter<T>(elementType)

        return RealmListAdapter(elementAdapter)

    }

}
