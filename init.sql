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

INSERT INTO doggies values ('BRM', 'Martins', 'Brandone',  'Brondon', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbrm.jpg?alt=media&token=f25980ed-b9fd-451e-90e3-3eed64989de7', 'WEBF', 'rit très fort');
INSERT INTO doggies values ('RDO', 'Dormoy', 'Remi',  'Rémido', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/2019-09-26T12%3A57%3A47.835Zyolo?alt=media&token=246f6e8f-2fa0-4974-baa3-2c63b5d75e69', 'CONEX', 'boit du Ricard');
INSERT INTO doggies values ('BEJ', 'Jarlier', 'Benoit',  'Bénoit', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbej.jpg?alt=media&token=821ad501-21f1-4250-9480-b32dec5b64a8', 'NAD', 'boit beaucoup');
INSERT INTO doggies values ('LAAL','Lambert' , 'Alizée',  'Alizouz', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Falizee.jpg?alt=media&token=8aedd71b-73e7-4bfb-bcf3-af4ef1049155', 'UX', 'voit le bon côté des choses');
INSERT INTO doggies values ('DAS', 'Sala', 'Daniel',  'Daniboy', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fdanyboy.jpg?alt=media&token=02490ee8-94a8-4385-8a1c-8d56a7aa187a', 'SCALE', 'a le plus beau boule du monde');
INSERT INTO doggies values ('MBI', 'Biardeau', 'Marie',  'Marizouz', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmarie.jpg?alt=media&token=e89b9025-35f3-40df-b6a0-3073b4372fe0', 'OCAC', 'boit des shots');
INSERT INTO doggies values ('JDO', 'Dompe', 'Juliette',  'Juju', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fjuliette.jpg?alt=media&token=6abf4e32-73e2-46e2-813b-79f0c454bb07', 'ARCHI', 'fait des cocktails');
INSERT INTO doggies values ('BME', 'Meriaux', 'Benoit',  'Renoit', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fbme.jpg?alt=media&token=2bd7adbd-a886-42a4-bcea-e41bdf69f49d', 'NAD', 'court très vite');
INSERT INTO doggies values ('FAM', 'Massin', 'Faustine',  'le bateau ', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Ffaustine.jpg?alt=media&token=f4909448-49cb-49cc-8e5f-d273cb927819', 'NAD', 'parle beaucoup de sexe');
INSERT INTO doggies values ('CLI', 'Liu', 'Can', 'Can', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcan.jpg?alt=media&token=4d648e28-ba9d-4cf9-a176-f34ce1f83a36', 'ARCHI', 'sait bien faire vibrer ses fesses');
INSERT INTO doggies values ('CEM', 'Martin', 'Cedric',  'Cédric', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcem.jpg?alt=media&token=ec8eefc5-8c8e-4ceb-bdff-8c7540084cab', 'NAD', 'maîtrise l''art de maîtriser');
INSERT INTO doggies values ('CYG', 'Guegan', 'Cyril',  'Cygounette', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fcyril.jpg?alt=media&token=2a26a82e-46af-4c06-8269-aa88285b4481', 'ARCHI', 'Met des grandes claques dans le dos');
INSERT INTO doggies values ('MEG', 'Menanteau', 'Mégane',  'Megazouz', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmegane.jpg?alt=media&token=d473bbb5-b521-41ff-8e0a-a454865a547d', 'WEBF', 'C''est une chaudière');
INSERT INTO doggies values ('JORO','Robert' , 'Joseph',  'JOROOOOO', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fjoseph.jpg?alt=media&token=82961ccf-3774-49c3-9476-cc769db5a723', 'ARCHI', 'Court très vite');
INSERT INTO doggies values ('LJA', 'Jacquemin', 'Leo',  'Léo', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fleo.jpg?alt=media&token=b6b16194-c0a5-4d7e-9bc5-feccaba555fc', 'WEBF', 'fait des dry january indéfiniment');
INSERT INTO doggies values ('MID', 'Durdevic', 'Mila',  'Mila', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fmila.jpg?alt=media&token=25b68c44-a34e-4536-a554-7f6c284e6324', 'CRAFT', 'Prend une grande caisse par an');
INSERT INTO doggies values ('SLU', 'Lundy', 'Stéphane', 'Le Mondai', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Fslu.jpg?alt=media&token=527e368e-75ec-413c-bdb5-1d8fc8482e9a', 'Autres', 'Maîtrise le feu');
INSERT INTO doggies values ('RBO', 'Botter', 'Rafaëlle', 'Le fantôme', 'https://firebasestorage.googleapis.com/v0/b/doggy-chat.appspot.com/o/doggy-photos%2Frafaelle.jpg?alt=media&token=d361b266-d31d-49d2-b682-52e9c2ba091f', 'Autres', 'parle beaucoup trop');

