import { Test, TestingModule } from '@nestjs/testing';
import { RecupererDoggy } from './recuperer-doggy.usecase';
import { DoggyRepository } from './doggy.repository';
import { Doggy } from './doggy.entity';

describe('RecupererDoggy', () => {
  let useCase: RecupererDoggy;
  let repository: DoggyRepository;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        RecupererDoggy,
        {
          provide: DoggyRepository,
          useValue: {
            findAll: jest.fn(),
          },
        },
      ],
    }).compile();

    useCase = module.get<RecupererDoggy>(RecupererDoggy);
    repository = module.get<DoggyRepository>(DoggyRepository);
  });

  it('should return all doggies', async () => {
    const doggies = [
      new Doggy('ABC', 'Doe', 'John', 'JD', 'photo.jpg', 'Tribu1', 'signe', 'john@test.com'),
      new Doggy('DEF', 'Smith', 'Jane', 'JS', 'photo2.jpg', 'Tribu2', 'signe2', 'jane@test.com'),
    ];

    jest.spyOn(repository, 'findAll').mockResolvedValue(doggies);

    const result = await useCase.execute();

    expect(result).toEqual(doggies);
    expect(repository.findAll).toHaveBeenCalled();
  });
});
