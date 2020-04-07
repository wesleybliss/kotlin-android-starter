import { Exclude } from 'class-transformer'

export default class User {
    
    id: number
    
    email: string
    
    @Exclude()
    password: string
    
    firstName: string
    lastName: string
    dob: Date
    gender: number
    
    address: string
    city: string
    state: string
    zipCode: number
    country: string
    phone: number
    
    avatar: string
    
    // constructor(id: number, props = {}) {
    //     this.id = id
        
    //     Object.keys(props).forEach(key => {
    //         this[key] = props[key]
    //     })
    // }
    constructor(partial: Partial<User>) {
        Object.assign(this, partial)
    }
    
}
