package com.gammagamma.kas.network.mapper

import com.gammagamma.kas.domain.net.INetworkResponseMapper
import com.gammagamma.kas.domain.response.AddressResponse
import com.gammagamma.kas.sqldelight.data.Address

@Suppress("MemberVisibilityCanBePrivate")
class AddressResponseMapper : INetworkResponseMapper<AddressResponse, Address> {
    
    override fun map(value: AddressResponse): Address = Address.Impl(
        id = value.id,
        street = value.street,
        suite = value.suite,
        city = value.city,
        zipcode = value.zipCode,
    )
    
    override fun map(values: List<AddressResponse>): List<Address> = values.map { map(it) }
    
}
