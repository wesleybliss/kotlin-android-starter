package com.gammagamma.kas.domain.net

/**
 * Maps a network response to a database class
 *
 * @param A Network response
 * @param B Target database class type
 */
interface INetworkResponseMapper<A: NetworkResponseObject, B> {
    
    fun map(value: A): B?
    
    fun map(values: List<A>): List<B?>
    
}
