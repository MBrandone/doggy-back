import { Injectable } from '@nestjs/common';
import { CitationRepository } from './citation.repository';
import { Citation } from './citation.entity';

@Injectable()
export class RecupererNouvelleCitation {
  constructor(private readonly citationRepository: CitationRepository) {}

  async execute(): Promise<Citation | null> {
    return this.citationRepository.findRandom();
  }
}
