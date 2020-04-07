import { Injectable } from '@nestjs/common'
import { db } from '../db'
import User from './user.entity'

@Injectable()
export class UsersService {
    
    private sanitize(user: User) {
        delete user.password
        return user
    }
    
    private sanitizeAll(users: User[]) {
        return users.map(this.sanitize)
    }
    
    async findAll(offset = 0, limit = 10): Promise<User[]> {
        const res = db.get('users')
        return this.sanitizeAll(res.slice(offset, (offset + limit)))
    }
    
    async findById(id: number): Promise<User | undefined> {
        return this.sanitize(db.get('users').find({ id }).value())
    }
    
    async findByEmail(email: string): Promise<User | undefined> {
        return this.sanitize(db.get('users').find({ email }).value())
    }
    
}
