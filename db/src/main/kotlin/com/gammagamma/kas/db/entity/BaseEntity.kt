package com.gammagamma.kas.db.entity

import android.os.Parcelable
import androidx.room.*
import org.threeten.bp.OffsetDateTime
import java.io.Serializable

@Entity
abstract class BaseEntity : IEntity, Parcelable {
    
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    
    @ColumnInfo(name = "created_at")
    override var createdAt: OffsetDateTime = OffsetDateTime.now()
    
    @ColumnInfo(name = "modified_at")
    override var modifiedAt: OffsetDateTime = OffsetDateTime.now()
    
}
