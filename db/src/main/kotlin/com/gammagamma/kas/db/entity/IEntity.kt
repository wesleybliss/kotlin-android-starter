package com.gammagamma.kas.db.entity

import org.threeten.bp.OffsetDateTime

interface IEntity {
    
    var createdAt: OffsetDateTime
    var modifiedAt: OffsetDateTime
    
}
