package com.gammagamma.kas.network.mapper

import com.gammagamma.kas.domain.response.AddressResponse
import com.gammagamma.kas.sqldelight.data.Address

class AddressResponseMapper {
    
    fun map(value: AddressResponse) = Address.Impl(
        value.id,
        value.street,
        value.suite,
        value.city,
        value.zipCode,
    )
    
    fun map(values: List<AddressResponse>) = values.map { map(it) }
    
}
