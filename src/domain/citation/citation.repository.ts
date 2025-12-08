import { Citation } from './citation.entity';

export abstract class CitationRepository {
  abstract findById(id: number): Promise<Citation | null>;
  abstract findRandom(): Promise<Citation | null>;
}
