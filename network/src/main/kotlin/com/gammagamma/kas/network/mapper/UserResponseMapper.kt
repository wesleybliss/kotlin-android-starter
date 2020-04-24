package com.gammagamma.kas.network.mapper

import com.gammagamma.kas.domain.net.INetworkResponseMapper
import com.gammagamma.kas.domain.response.UserResponse
import com.gammagamma.kas.sqldelight.data.User

class UserResponseMapper : INetworkResponseMapper<UserResponse, User> {
    
    override fun map(value: UserResponse): User = User.Impl(
        value.id,
        value.email,
        value.name,
        value.address?.id,
        value.phone,
    )
    
    override fun map(values: List<UserResponse>): List<User> = values.map { map(it) }
    
}
