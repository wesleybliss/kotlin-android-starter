import 'reflect-metadata'
import * as fs from 'fs'
import { NestFactory } from '@nestjs/core'
import {
    FastifyAdapter,
    NestFastifyApplication,
} from '@nestjs/platform-fastify'
import { AppModule } from './app.module'

const httpsOptions = {
    key: fs.readFileSync('./secrets/key.pem'),
    cert: fs.readFileSync('./secrets/cert.pem'),
}

async function bootstrap() {
    
    // const app = await NestFactory.create<NestFastifyApplication>(
    //     AppModule,
    //     new FastifyAdapter()
    // )
    const app = await NestFactory.create(AppModule, {
        httpsOptions,
    })
    
    await app.listen(3000, '0.0.0.0')
    
}

bootstrap()
