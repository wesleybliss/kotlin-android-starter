import {
    Controller,
    Get,
    Param,
    Query
} from '@nestjs/common'
import { UsersService } from './users.service'
import User from './user.entity'

@Controller('users')
export class UsersController {
    
    constructor(private readonly usersService: UsersService) {}
    
    @Get()
    async findAll(
        @Query('offset') offset = '0',
        @Query('limit') limit = '10',
        @Query('email') email: string
    ): Promise<User|User[]> {
        if (email && email.length)
            return this.usersService.findByEmail(email)
        else
            return this.usersService.findAll(
                parseInt(offset, 10),
                parseInt(limit, 10)
            )
    }
    
    @Get(':id')
    async findById(
        @Param() params: any
    ): Promise<User> {
        return this.usersService.findById(parseInt(params.id, 10))
    }
    
}
