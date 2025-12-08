import { Doggy } from './doggy.entity';

export abstract class DoggyRepository {
  abstract findAll(): Promise<Doggy[]>;
  abstract findByTrigramme(trigramme: string): Promise<Doggy | null>;
  abstract findByEmail(email: string): Promise<Doggy | null>;
}
