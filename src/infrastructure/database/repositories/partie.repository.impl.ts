import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { PartieRepository } from '../../../domain/partie/partie.repository';
import { Partie } from '../../../domain/partie/partie.entity';
import { PartieEntity } from '../entities/partie.entity.typeorm';

@Injectable()
export class PartieRepositoryImpl implements PartieRepository {
  constructor(
    @InjectRepository(PartieEntity)
    private readonly partieRepository: Repository<PartieEntity>,
  ) {}

  async save(partie: Partie): Promise<void> {
    const entity = this.toEntity(partie);
    await this.partieRepository.save(entity);
  }

  async findById(id: string): Promise<Partie | null> {
    const entity = await this.partieRepository.findOne({
      where: { id },
    });
    return entity ? this.toDomain(entity) : null;
  }

  private toDomain(entity: PartieEntity): Partie {
    return new Partie(entity.id, entity.joueur, entity.score, entity.statut);
  }

  private toEntity(partie: Partie): PartieEntity {
    const entity = new PartieEntity();
    entity.id = partie.id;
    entity.joueur = partie.joueur;
    entity.score = partie.score;
    entity.statut = partie.statut;
    return entity;
  }
}
