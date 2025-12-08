import { Entity, Column, PrimaryColumn, ManyToOne, JoinColumn } from 'typeorm';
import { CitationEntity } from './citation.entity.typeorm';
import { DoggyEntity } from './doggy.entity.typeorm';

@Entity('auteurs_citations')
export class AuteurCitationEntity {
  @PrimaryColumn({ name: 'id_citation', type: 'int' })
  idCitation: number;

  @PrimaryColumn({ type: 'varchar', length: 255 })
  trigramme: string;

  @ManyToOne(() => CitationEntity)
  @JoinColumn({ name: 'id_citation' })
  citation: CitationEntity;

  @ManyToOne(() => DoggyEntity)
  @JoinColumn({ name: 'trigramme' })
  doggy: DoggyEntity;
}
