import { Entity, Column, PrimaryColumn } from 'typeorm';

@Entity('doggies')
export class DoggyEntity {
  @PrimaryColumn({ type: 'varchar', length: 255 })
  trigramme: string;

  @Column({ type: 'varchar', length: 255 })
  nom: string;

  @Column({ type: 'varchar', length: 255 })
  prenom: string;

  @Column({ type: 'varchar', length: 255 })
  surnom: string;

  @Column({ type: 'varchar', length: 255 })
  photo: string;

  @Column({ type: 'varchar', length: 255 })
  tribu: string;

  @Column({ type: 'varchar', length: 255, name: 'signe_particulier' })
  signeParticulier: string;

  @Column({ type: 'varchar', length: 255 })
  email: string;
}
