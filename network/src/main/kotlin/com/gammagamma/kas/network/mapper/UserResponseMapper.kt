package com.gammagamma.kas.network.mapper

import com.gammagamma.kas.domain.response.UserResponse
import com.gammagamma.kas.sqldelight.data.User

class UserResponseMapper() {
    
    private val addressMapper = AddressResponseMapper()
    
    fun map(value: UserResponse) = User.Impl(
        value.id,
        value.email,
        value.name,
        value.address?.id,
        value.phone,
    )
    
    fun map(values: List<UserResponse>) = values.map { map(it) }
    
}
