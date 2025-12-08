import { ApiProperty } from '@nestjs/swagger';
import { DoggyDto } from './doggy.dto';

export class CitationDto {
  @ApiProperty()
  id: number;

  @ApiProperty()
  texte: string;

  @ApiProperty({ type: [DoggyDto] })
  auteurs: DoggyDto[];
}
