import { Test, TestingModule } from '@nestjs/testing';
import { CorrigerReponse, ReponseDto } from './corriger-reponse.usecase';
import { PartieRepository } from '../partie/partie.repository';
import { CitationRepository } from './citation.repository';
import { Partie } from '../partie/partie.entity';
import { Citation } from './citation.entity';
import { Doggy } from '../doggy/doggy.entity';
import { PartieStatut } from '../partie/partie-statut.enum';
import { NotFoundException, BadRequestException } from '@nestjs/common';

describe('CorrigerReponse', () => {
  let useCase: CorrigerReponse;
  let partieRepository: PartieRepository;
  let citationRepository: CitationRepository;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        CorrigerReponse,
        {
          provide: PartieRepository,
          useValue: {
            findById: jest.fn(),
          },
        },
        {
          provide: CitationRepository,
          useValue: {
            findById: jest.fn(),
          },
        },
      ],
    }).compile();

    useCase = module.get<CorrigerReponse>(CorrigerReponse);
    partieRepository = module.get<PartieRepository>(PartieRepository);
    citationRepository = module.get<CitationRepository>(CitationRepository);
  });

  it('should throw NotFoundException when partie does not exist', async () => {
    jest.spyOn(partieRepository, 'findById').mockResolvedValue(null);

    const reponse: ReponseDto = { idCitation: 1, texte: 'ABC' };

    await expect(useCase.execute('unknown-id', reponse)).rejects.toThrow(
      NotFoundException,
    );
  });

  it('should return true for correct answer', async () => {
    const doggy = new Doggy('ABC', 'Doe', 'John', 'JD', 'photo.jpg', 'Tribu1', 'signe', 'john@test.com');
    const partie = new Partie('partie-1', 'player1', 0, PartieStatut.EN_COURS);
    const citation = new Citation(1, 'Test quote', [doggy]);

    jest.spyOn(partieRepository, 'findById').mockResolvedValue(partie);
    jest.spyOn(citationRepository, 'findById').mockResolvedValue(citation);

    const reponse: ReponseDto = { idCitation: 1, texte: 'ABC' };
    const result = await useCase.execute('partie-1', reponse);

    expect(result).toBe(true);
  });

  it('should return false for incorrect answer', async () => {
    const doggy = new Doggy('ABC', 'Doe', 'John', 'JD', 'photo.jpg', 'Tribu1', 'signe', 'john@test.com');
    const partie = new Partie('partie-1', 'player1', 0, PartieStatut.EN_COURS);
    const citation = new Citation(1, 'Test quote', [doggy]);

    jest.spyOn(partieRepository, 'findById').mockResolvedValue(partie);
    jest.spyOn(citationRepository, 'findById').mockResolvedValue(citation);

    const reponse: ReponseDto = { idCitation: 1, texte: 'XYZ' };
    const result = await useCase.execute('partie-1', reponse);

    expect(result).toBe(false);
  });
});
