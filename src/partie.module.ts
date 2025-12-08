import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PartieEntity } from './infrastructure/database/entities/partie.entity.typeorm';
import { PartieRepository } from './domain/partie/partie.repository';
import { PartieRepositoryImpl } from './infrastructure/database/repositories/partie.repository.impl';
import { CreerPartie } from './domain/partie/creer-partie.usecase';
import { PartieController } from './presentation/controllers/partie.controller';
import { CitationModule } from './citation.module';

@Module({
  imports: [TypeOrmModule.forFeature([PartieEntity]), CitationModule],
  providers: [
    {
      provide: PartieRepository,
      useClass: PartieRepositoryImpl,
    },
    CreerPartie,
  ],
  controllers: [PartieController],
  exports: [PartieRepository],
})
export class PartieModule {}
