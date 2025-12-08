import { Controller, Post, Body, Param } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { CreerPartie } from '../../domain/partie/creer-partie.usecase';
import { CorrigerReponse } from '../../domain/citation/corriger-reponse.usecase';
import { PartieDto } from '../dto/partie.dto';
import { ReponseDto } from '../dto/reponse.dto';
import { PartieMapper } from '../mappers/partie.mapper';

@Controller('parties')
@ApiTags('Parties')
export class PartieController {
  constructor(
    private readonly creerPartie: CreerPartie,
    private readonly corrigerReponse: CorrigerReponse,
  ) {}

  @Post()
  @ApiOperation({ summary: 'Créer une nouvelle partie' })
  async postPartie(@Body() joueur: string): Promise<PartieDto> {
    const partie = await this.creerPartie.execute(joueur);
    return PartieMapper.toDto(partie);
  }

  @Post(':idPartie/reponse')
  @ApiOperation({ summary: 'Répondre à un défi' })
  async repondreAuDefi(
    @Param('idPartie') idPartie: string,
    @Body() reponse: ReponseDto,
  ): Promise<boolean> {
    return this.corrigerReponse.execute(idPartie, reponse);
  }
}
