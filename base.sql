

create database Jmessenger;
use Jmessenger;

create table utilisateurs ( pseudo VARCHAR(10), mdp VARCHAR(16) );

INSERT INTO utilisateurs (pseudo, mdp) 
    VALUES ('benjo', 'oui');
    
DELETE FROM utilisateurs;

select * from utilisateurs

commit;