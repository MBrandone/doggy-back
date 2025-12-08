import { Injectable, NotFoundException, BadRequestException } from '@nestjs/common';
import { PartieRepository } from '../partie/partie.repository';
import { CitationRepository } from './citation.repository';
import { PartieStatut } from '../partie/partie-statut.enum';

export class ReponseDto {
  idCitation: number;
  texte: string;
}

@Injectable()
export class CorrigerReponse {
  constructor(
    private readonly partieRepository: PartieRepository,
    private readonly citationRepository: CitationRepository,
  ) {}

  async execute(idPartie: string, reponse: ReponseDto): Promise<boolean> {
    const partie = await this.partieRepository.findById(idPartie);

    if (!partie) {
      throw new NotFoundException(`Tema wesh, y a pas de partie avec l'id ${idPartie} !`);
    }

    if (partie.statut !== PartieStatut.EN_COURS) {
      throw new BadRequestException(`Tema wesh, la partie avec l'id ${idPartie}, elle est terminé !`);
    }

    return this.corriger(reponse);
  }

  private async corriger(reponse: ReponseDto): Promise<boolean> {
    const citation = await this.citationRepository.findById(reponse.idCitation);

    if (!citation) {
      throw new BadRequestException(`Tema wesh, le défi avec l'id ${reponse.idCitation}, il existe pas !`);
    }

    return citation.auteurs.some((auteur) => auteur.trigramme === reponse.texte);
  }
}
