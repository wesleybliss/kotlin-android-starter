package com.gammagamma.kas.domain.db

import com.gammagamma.kas.sqldelight.Database
import com.gammagamma.kas.sqldelight.data.Address

abstract class AAddressDao(db: Database) : ADao<Address>(db)