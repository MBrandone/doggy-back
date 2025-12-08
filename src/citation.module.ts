import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CitationEntity } from './infrastructure/database/entities/citation.entity.typeorm';
import { AuteurCitationEntity } from './infrastructure/database/entities/auteur-citation.entity.typeorm';
import { CitationRepository } from './domain/citation/citation.repository';
import { CitationRepositoryImpl } from './infrastructure/database/repositories/citation.repository.impl';
import { CorrigerReponse } from './domain/citation/corriger-reponse.usecase';
import { RecupererNouvelleCitation } from './domain/citation/recuperer-nouvelle-citation.usecase';
import { CitationController } from './presentation/controllers/citation.controller';
import { PartieRepository } from './domain/partie/partie.repository';
import { PartieRepositoryImpl } from './infrastructure/database/repositories/partie.repository.impl';
import { PartieEntity } from './infrastructure/database/entities/partie.entity.typeorm';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      CitationEntity,
      AuteurCitationEntity,
      PartieEntity,
    ]),
  ],
  providers: [
    {
      provide: CitationRepository,
      useClass: CitationRepositoryImpl,
    },
    {
      provide: PartieRepository,
      useClass: PartieRepositoryImpl,
    },
    CorrigerReponse,
    RecupererNouvelleCitation,
  ],
  controllers: [CitationController],
  exports: [CitationRepository, CorrigerReponse],
})
export class CitationModule {}
