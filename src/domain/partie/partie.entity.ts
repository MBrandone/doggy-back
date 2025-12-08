import { v4 as uuidv4 } from 'uuid';
import { PartieStatut } from './partie-statut.enum';

export class Partie {
  constructor(
    public readonly id: string,
    public readonly joueur: string,
    public readonly score: number,
    public readonly statut: PartieStatut,
  ) {}

  static init(joueur: string): Partie {
    return new Partie(uuidv4(), joueur, 0, PartieStatut.EN_COURS);
  }
}
