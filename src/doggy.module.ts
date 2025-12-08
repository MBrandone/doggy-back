import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DoggyEntity } from './infrastructure/database/entities/doggy.entity.typeorm';
import { DoggyRepository } from './domain/doggy/doggy.repository';
import { DoggyRepositoryImpl } from './infrastructure/database/repositories/doggy.repository.impl';
import { RecupererDoggy } from './domain/doggy/recuperer-doggy.usecase';
import { RecupererUnDoggy } from './domain/doggy/recuperer-un-doggy.usecase';
import { DoggyController } from './presentation/controllers/doggy.controller';

@Module({
  imports: [TypeOrmModule.forFeature([DoggyEntity])],
  providers: [
    {
      provide: DoggyRepository,
      useClass: DoggyRepositoryImpl,
    },
    RecupererDoggy,
    RecupererUnDoggy,
  ],
  controllers: [DoggyController],
  exports: [DoggyRepository],
})
export class DoggyModule {}
