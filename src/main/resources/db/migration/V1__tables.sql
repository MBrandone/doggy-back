create table parties(
    id varchar(255) PRIMARY KEY UNIQUE,
    joueur varchar(255),
    score int,
    statut varchar(255)
);


create table doggies(
    trigramme varchar(255) PRIMARY KEY UNIQUE,
    nom varchar(255),
    prenom varchar(255),
    surnom varchar(255),
    photo varchar(255),
    tribu varchar(255),
    signe_particulier varchar(255)
);