

create database Jmessenger;
use Jmessenger;

create table utilisateurs ( pseudo VARCHAR(10), mdp VARCHAR(16) );
create table salons( membre VARCHAR(10) );

INSERT INTO utilisateurs (pseudo, mdp) 
    VALUES ('benjo', 'oui');
    
DELETE FROM utilisateurs;

UPDATE utilisateurs SET salon = 'Les BROKC' WHERE pseudo = 'Benjo';

UPDATE utilisateurs SET mdp = 'a' WHERE pseudo = 'Benjo';

select * from utilisateurs;

select * from salons;
commit;