import { Partie } from './partie.entity';

export abstract class PartieRepository {
  abstract save(partie: Partie): Promise<void>;
  abstract findById(id: string): Promise<Partie | null>;
}
