import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { DoggyRepository } from '../../../domain/doggy/doggy.repository';
import { Doggy } from '../../../domain/doggy/doggy.entity';
import { DoggyEntity } from '../entities/doggy.entity.typeorm';

@Injectable()
export class DoggyRepositoryImpl implements DoggyRepository {
  constructor(
    @InjectRepository(DoggyEntity)
    private readonly doggyRepository: Repository<DoggyEntity>,
  ) {}

  async findAll(): Promise<Doggy[]> {
    const entities = await this.doggyRepository.find();
    return entities.map(this.toDomain);
  }

  async findByTrigramme(trigramme: string): Promise<Doggy | null> {
    const entity = await this.doggyRepository.findOne({
      where: { trigramme },
    });
    return entity ? this.toDomain(entity) : null;
  }

  async findByEmail(email: string): Promise<Doggy | null> {
    const entity = await this.doggyRepository.findOne({
      where: { email },
    });
    return entity ? this.toDomain(entity) : null;
  }

  private toDomain(entity: DoggyEntity): Doggy {
    return new Doggy(
      entity.trigramme,
      entity.nom,
      entity.prenom,
      entity.surnom,
      entity.photo,
      entity.tribu,
      entity.signeParticulier,
      entity.email,
    );
  }
}
