import { Module } from '@nestjs/common';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DoggyModule } from './doggy.module';
import { PartieModule } from './partie.module';
import { CitationModule } from './citation.module';
import { NotificationModule } from './notification.module';
import { DoggyEntity } from './infrastructure/database/entities/doggy.entity.typeorm';
import { PartieEntity } from './infrastructure/database/entities/partie.entity.typeorm';
import { CitationEntity } from './infrastructure/database/entities/citation.entity.typeorm';
import { AuteurCitationEntity } from './infrastructure/database/entities/auteur-citation.entity.typeorm';
import { GoogleAuthService } from './infrastructure/external/google-auth/google-auth.service';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: (configService: ConfigService) => ({
        type: 'postgres',
        url: configService.get<string>('DATABASE_URL'),
        entities: [
          DoggyEntity,
          PartieEntity,
          CitationEntity,
          AuteurCitationEntity,
        ],
        synchronize: false,
        logging: false,
        ssl:
          process.env.NODE_ENV === 'production'
            ? { rejectUnauthorized: false }
            : false,
      }),
      inject: [ConfigService],
    }),
    DoggyModule,
    PartieModule,
    CitationModule,
    NotificationModule,
  ],
  providers: [GoogleAuthService],
})
export class AppModule {}
