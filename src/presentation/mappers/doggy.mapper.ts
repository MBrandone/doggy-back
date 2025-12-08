import { Doggy } from '../../domain/doggy/doggy.entity';
import { DoggyDto } from '../dto/doggy.dto';

export class DoggyMapper {
  static toDto(doggy: Doggy): DoggyDto {
    const dto = new DoggyDto();
    dto.trigramme = doggy.trigramme;
    dto.nom = doggy.nom;
    dto.prenom = doggy.prenom;
    dto.surnom = doggy.surnom;
    dto.photo = doggy.photo;
    dto.tribu = doggy.tribu;
    dto.signeParticulier = doggy.signeParticulier;
    dto.email = doggy.email;
    return dto;
  }
}
