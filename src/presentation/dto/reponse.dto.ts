import { ApiProperty } from '@nestjs/swagger';
import { IsInt, IsString } from 'class-validator';

export class ReponseDto {
  @ApiProperty()
  @IsInt()
  idCitation: number;

  @ApiProperty()
  @IsString()
  texte: string;
}
