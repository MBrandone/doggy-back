import { Injectable } from '@nestjs/common';
import { PartieRepository } from './partie.repository';
import { Partie } from './partie.entity';

@Injectable()
export class CreerPartie {
  constructor(private readonly partieRepository: PartieRepository) {}

  async execute(joueur: string): Promise<Partie> {
    const partie = Partie.init(joueur);
    await this.partieRepository.save(partie);
    return partie;
  }
}
