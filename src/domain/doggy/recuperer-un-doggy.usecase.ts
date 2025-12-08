import { Injectable, NotFoundException } from '@nestjs/common';
import { DoggyRepository } from './doggy.repository';
import { Doggy } from './doggy.entity';

@Injectable()
export class RecupererUnDoggy {
  constructor(private readonly doggyRepository: DoggyRepository) {}

  async execute(trigramme: string): Promise<Doggy> {
    const doggy = await this.doggyRepository.findByTrigramme(trigramme);
    if (!doggy) {
      throw new NotFoundException(`Doggy with trigramme ${trigramme} not found`);
    }
    return doggy;
  }
}
