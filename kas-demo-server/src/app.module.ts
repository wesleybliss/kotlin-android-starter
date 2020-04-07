import { Module } from '@nestjs/common'
import { AppController } from './app.controller'
import { AppService } from './app.service'
import { AuthModule } from './auth/auth.module';
import { UsersService } from './users/users.service';
import { UsersController } from './users/users.controller';
import { UsersModule } from './users/users.module';

@Module({
    imports: [AuthModule, UsersModule],
    controllers: [AppController, UsersController],
    providers: [AppService, UsersService],
})

export class AppModule {}
