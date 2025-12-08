import { Controller, Get, Param } from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { RecupererDoggy } from '../../domain/doggy/recuperer-doggy.usecase';
import { RecupererUnDoggy } from '../../domain/doggy/recuperer-un-doggy.usecase';
import { DoggyDto } from '../dto/doggy.dto';
import { DoggyMapper } from '../mappers/doggy.mapper';

@Controller('doggies')
@ApiTags('Doggy')
export class DoggyController {
  constructor(
    private readonly recupererDoggy: RecupererDoggy,
    private readonly recupererUnDoggy: RecupererUnDoggy,
  ) {}

  @Get()
  @ApiOperation({
    summary: 'Pour récupérer la liste de tous les doggy',
    description: "Si t'es pas dedans ben t'es un looseur",
  })
  async getDoggies(): Promise<DoggyDto[]> {
    const doggies = await this.recupererDoggy.execute();
    return doggies.map(DoggyMapper.toDto);
  }

  @Get(':trigramme')
  @ApiOperation({
    summary: 'Pour récupérer un seul doggy',
    description: 'Essaye pas JOL, il est parti',
  })
  async getDoggy(@Param('trigramme') trigramme: string): Promise<DoggyDto> {
    const doggy = await this.recupererUnDoggy.execute(trigramme);
    return DoggyMapper.toDto(doggy);
  }
}
