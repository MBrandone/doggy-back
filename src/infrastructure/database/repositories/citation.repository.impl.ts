import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CitationRepository } from '../../../domain/citation/citation.repository';
import { Citation } from '../../../domain/citation/citation.entity';
import { Doggy } from '../../../domain/doggy/doggy.entity';
import { CitationEntity } from '../entities/citation.entity.typeorm';
import { AuteurCitationEntity } from '../entities/auteur-citation.entity.typeorm';

@Injectable()
export class CitationRepositoryImpl implements CitationRepository {
  constructor(
    @InjectRepository(CitationEntity)
    private readonly citationRepository: Repository<CitationEntity>,
    @InjectRepository(AuteurCitationEntity)
    private readonly auteurCitationRepository: Repository<AuteurCitationEntity>,
  ) {}

  async findById(id: number): Promise<Citation | null> {
    const entity = await this.citationRepository.findOne({
      where: { id },
    });

    if (!entity) {
      return null;
    }

    const auteurs = await this.getAuteurs(id);
    return new Citation(entity.id, entity.texte, auteurs);
  }

  async findRandom(): Promise<Citation | null> {
    const result = await this.citationRepository.query(
      'SELECT * FROM citations ORDER BY RANDOM() LIMIT 1',
    );

    if (!result || result.length === 0) {
      return null;
    }

    const entity = result[0];
    const auteurs = await this.getAuteurs(entity.id);
    return new Citation(entity.id, entity.texte, auteurs);
  }

  private async getAuteurs(citationId: number): Promise<Doggy[]> {
    const auteurEntities = await this.auteurCitationRepository.find({
      where: { idCitation: citationId },
      relations: ['doggy'],
    });

    return auteurEntities.map((ac) => {
      const d = ac.doggy;
      return new Doggy(
        d.trigramme,
        d.nom,
        d.prenom,
        d.surnom,
        d.photo,
        d.tribu,
        d.signeParticulier,
        d.email,
      );
    });
  }
}
