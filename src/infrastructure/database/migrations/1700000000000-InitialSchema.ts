import { MigrationInterface, QueryRunner } from 'typeorm';

export class InitialSchema1700000000000 implements MigrationInterface {
  public async up(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.query(`
      CREATE TABLE parties(
        id varchar(255) PRIMARY KEY UNIQUE,
        joueur varchar(255),
        score int,
        statut varchar(255)
      );
    `);

    await queryRunner.query(`
      CREATE TABLE doggies(
        trigramme varchar(255) PRIMARY KEY UNIQUE,
        nom varchar(255),
        prenom varchar(255),
        surnom varchar(255),
        photo varchar(255),
        tribu varchar(255),
        signe_particulier varchar(255),
        email varchar(255)
      );
    `);

    await queryRunner.query(`
      CREATE TABLE citations(
        id INTEGER PRIMARY KEY UNIQUE,
        texte varchar(500)
      );
    `);

    await queryRunner.query(`
      CREATE TABLE auteurs_citations(
        id_citation INTEGER,
        trigramme varchar(255),
        FOREIGN KEY (id_citation) REFERENCES citations(id),
        FOREIGN KEY (trigramme) REFERENCES doggies(trigramme)
      );
    `);
  }

  public async down(queryRunner: QueryRunner): Promise<void> {
    await queryRunner.query(`DROP TABLE auteurs_citations`);
    await queryRunner.query(`DROP TABLE citations`);
    await queryRunner.query(`DROP TABLE doggies`);
    await queryRunner.query(`DROP TABLE parties`);
  }
}
