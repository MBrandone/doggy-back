import { Partie } from '../../domain/partie/partie.entity';
import { PartieDto } from '../dto/partie.dto';

export class PartieMapper {
  static toDto(partie: Partie): PartieDto {
    const dto = new PartieDto();
    dto.id = partie.id;
    dto.joueur = partie.joueur;
    dto.score = partie.score;
    dto.statut = partie.statut;
    return dto;
  }
}
