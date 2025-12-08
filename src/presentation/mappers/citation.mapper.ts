import { Citation } from '../../domain/citation/citation.entity';
import { CitationDto } from '../dto/citation.dto';
import { DoggyMapper } from './doggy.mapper';

export class CitationMapper {
  static toDto(citation: Citation): CitationDto {
    const dto = new CitationDto();
    dto.id = citation.id;
    dto.texte = citation.texte;
    dto.auteurs = citation.auteurs.map(DoggyMapper.toDto);
    return dto;
  }
}
