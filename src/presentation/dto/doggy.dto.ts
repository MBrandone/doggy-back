import { ApiProperty } from '@nestjs/swagger';

export class DoggyDto {
  @ApiProperty()
  trigramme: string;

  @ApiProperty()
  nom: string;

  @ApiProperty()
  prenom: string;

  @ApiProperty()
  surnom: string;

  @ApiProperty()
  photo: string;

  @ApiProperty()
  tribu: string;

  @ApiProperty()
  signeParticulier: string;

  @ApiProperty()
  email: string;
}
