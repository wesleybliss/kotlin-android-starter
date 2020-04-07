import { Controller, Get } from '@nestjs/common'
import { AppService } from './app.service'
import { initDatabase, seedUsers } from './db'

@Controller()
export class AppController {
    
    constructor(private readonly appService: AppService) {
        initDatabase()
        seedUsers()
    }
    
    @Get()
    getHello(): string {
        return this.appService.getHello()
    }
    
}
