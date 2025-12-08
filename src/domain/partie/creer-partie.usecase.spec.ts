import { Test, TestingModule } from '@nestjs/testing';
import { CreerPartie } from './creer-partie.usecase';
import { PartieRepository } from './partie.repository';

describe('CreerPartie', () => {
  let useCase: CreerPartie;
  let repository: PartieRepository;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        CreerPartie,
        {
          provide: PartieRepository,
          useValue: {
            save: jest.fn(),
          },
        },
      ],
    }).compile();

    useCase = module.get<CreerPartie>(CreerPartie);
    repository = module.get<PartieRepository>(PartieRepository);
  });

  it('should create a new partie', async () => {
    const joueur = 'TestPlayer';
    jest.spyOn(repository, 'save').mockResolvedValue(undefined);

    const result = await useCase.execute(joueur);

    expect(result.joueur).toBe(joueur);
    expect(result.score).toBe(0);
    expect(result.id).toBeDefined();
    expect(repository.save).toHaveBeenCalledWith(result);
  });
});
