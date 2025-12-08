import { Entity, Column, PrimaryColumn } from 'typeorm';
import { PartieStatut } from '../../../domain/partie/partie-statut.enum';

@Entity('parties')
export class PartieEntity {
  @PrimaryColumn({ type: 'varchar', length: 255 })
  id: string;

  @Column({ type: 'varchar', length: 255 })
  joueur: string;

  @Column({ type: 'int' })
  score: number;

  @Column({
    type: 'varchar',
    length: 255,
  })
  statut: PartieStatut;
}
