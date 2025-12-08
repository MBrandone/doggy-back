import { Entity, Column, PrimaryColumn } from 'typeorm';

@Entity('citations')
export class CitationEntity {
  @PrimaryColumn({ type: 'int' })
  id: number;

  @Column({ type: 'varchar', length: 500 })
  texte: string;
}
