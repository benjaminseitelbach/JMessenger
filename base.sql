

create database Jmessenger;
use Jmessenger;

create table utilisateurs ( pseudo VARCHAR(10), mdp VARCHAR(16) );
create table salons( nom VARCHAR(10) );

INSERT INTO utilisateurs (pseudo, mdp) 
    VALUES ('benjo', 'oui');

ALTER TABLE utilisateurs DROP nombreSalons;  
ALTER TABLE utilisateurs ADD nombreSalons INT(1); 
DELETE FROM utilisateurs;
DELETE FROM salons;
ALTER TABLE salons ADD nombreMembres INT(1);
UPDATE utilisateurs SET nombreSalons = 0 WHERE pseudo = 'Alois';

UPDATE utilisateurs SET mdp = 'a' WHERE pseudo = 'Benjo';
UPDATE salons SET createur = 'Benjo' WHERE nom = 'Les BROKC';
INSERT INTO salons (nom) VALUES ('Les BROKC');
INSERT INTO salons (createur) VALUES ('Benjo');
select * from utilisateurs;

select * from salons;
commit;