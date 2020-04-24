package com.gammagamma.kas.domain.db

import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.User

abstract class AUserDao(db: Database) : ADao<User>(db)
