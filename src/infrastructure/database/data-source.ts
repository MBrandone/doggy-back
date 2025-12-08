import { DataSource } from 'typeorm';
import { DoggyEntity } from './entities/doggy.entity.typeorm';
import { PartieEntity } from './entities/partie.entity.typeorm';
import { CitationEntity } from './entities/citation.entity.typeorm';
import { AuteurCitationEntity } from './entities/auteur-citation.entity.typeorm';

export const AppDataSource = new DataSource({
  type: 'postgres',
  url: process.env.DATABASE_URL,
  entities: [DoggyEntity, PartieEntity, CitationEntity, AuteurCitationEntity],
  migrations: ['src/infrastructure/database/migrations/*.ts'],
  synchronize: false,
  logging: false,
  ssl: process.env.NODE_ENV === 'production' ? { rejectUnauthorized: false } : false,
});
