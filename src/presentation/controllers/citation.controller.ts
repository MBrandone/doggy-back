import { Controller, Get } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { RecupererNouvelleCitation } from '../../domain/citation/recuperer-nouvelle-citation.usecase';
import { CitationDto } from '../dto/citation.dto';
import { CitationMapper } from '../mappers/citation.mapper';

@Controller('citations')
@ApiTags('Citations')
export class CitationController {
  constructor(
    private readonly recupererNouvelleCitation: RecupererNouvelleCitation,
  ) {}

  @Get('random')
  @ApiOperation({ summary: 'Récupérer une citation aléatoire' })
  async getRandomCitation(): Promise<CitationDto | null> {
    const citation = await this.recupererNouvelleCitation.execute();
    return citation ? CitationMapper.toDto(citation) : null;
  }
}
