@echo off
echo RUN ON PGADMIN
echo create table livre (isbn char(4) not null primary key,
echo titre char(20),
echo dispo smallint default 1 not null, check (dispo in (0,1))); 

pause