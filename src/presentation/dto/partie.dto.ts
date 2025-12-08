import { ApiProperty } from '@nestjs/swagger';
import { PartieStatut } from '../../domain/partie/partie-statut.enum';

export class PartieDto {
  @ApiProperty()
  id: string;

  @ApiProperty()
  joueur: string;

  @ApiProperty()
  score: number;

  @ApiProperty({ enum: PartieStatut })
  statut: PartieStatut;
}
