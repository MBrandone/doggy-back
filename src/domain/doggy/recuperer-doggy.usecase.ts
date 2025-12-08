import { Injectable } from '@nestjs/common';
import { DoggyRepository } from './doggy.repository';
import { Doggy } from './doggy.entity';

@Injectable()
export class RecupererDoggy {
  constructor(private readonly doggyRepository: DoggyRepository) {}

  async execute(): Promise<Doggy[]> {
    return this.doggyRepository.findAll();
  }
}
