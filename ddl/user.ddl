-- beide teamgenoten moeten deze code uitvoeren OP DEZELFDE DATABASE
SELECT user_administration.set_session_lvg('webontwerp','2TX36')
SELECT user_administration.new_local_user('webontwerp');

--Database creeren op poort 52223 ipv 62223 !

CREATE SCHEMA IF NOT EXISTS groep2_13;

CREATE SEQUENCE groep2_13.user_id_seq;

CREATE TABLE groep2_13.users
(
    userid      integer   NOT NULL,
    first_name  char(40)  NOT NULL,
    last_name   char(40)  NOT NULL,
    email       char(40)  NOT NULL,
    role        char(40)  NOT NULL,
    team        char(40)  NOT NULL,
    password    char(40)  NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (userid)
);

grant all on schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on sequence groep2_13.user_id_seq to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on all tables in schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;


INSERT INTO groep2_13.users(userid, first_name, last_name, email, role, team, password)
VALUES (1, 'bob', 'de bouwer', 'bob@debouwer.be','employee','alpha','b');

set search_path to groep2_13;
select *
from groep2_13.users