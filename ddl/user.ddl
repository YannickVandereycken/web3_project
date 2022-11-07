-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

-- beide teamgenoten moeten deze code uitvoeren OP DEZELFDE DATABASE
SELECT user_administration.set_session_lvg('webontwerp', '2TX36')
SELECT user_administration.new_local_user('webontwerp');

--Database creeren op poort 52223 ipv 62223 !

CREATE SCHEMA IF NOT EXISTS groep2_13;

CREATE SEQUENCE groep2_13.user_id_seq;

CREATE TABLE groep2_13.users
(
    userid     integer   NOT NULL DEFAULT nextval('groep2_13.user_id_seq'::regclass),
    first_name char(40)  NOT NULL,
    last_name  char(40)  NOT NULL,
    email      char(40)  NOT NULL,
    role       char(40)  NOT NULL,
    team       char(40)  NOT NULL,
    password   char(128) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (userid)
);

grant all on schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on all SEQUENCES IN SCHEMA groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on all tables in schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;

--grant all on sequence groep2_13.user_id_seq to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;

-- director@ucll.be met wachtwoord t (rol: director; team: Alpha)
-- teamleader@ucll.be met wachtwoord t (rol: teamleader; team: Beta)
-- employee@ucll.be met wachtwoord t (rol: employee; team: Beta)

INSERT INTO groep2_13.users(userid, first_name, last_name, email, role, team, password)
VALUES (1, 'director', 'ucll', 'director@ucll.be', 'director', 'ALPHA', 't');
INSERT INTO groep2_13.users(userid, first_name, last_name, email, role, team, password)
VALUES (2, 'teamleader', 'ucll', 'teamleader@ucll.be', 'teamleader', 'BETA', 't');
INSERT INTO groep2_13.users(userid, first_name, last_name, email, role, team, password)
VALUES (3, 'employee', 'ucll', 'employee@ucll.be', 'employee', 'BETA', 't');
INSERT INTO groep2_13.users(userid, first_name, last_name, email, role, team, password)
VALUES (4, 'Bob', 'De Bouwer', 'a@a.be', 'director', 'ALPHA', 'a');

set search_path to groep2_13;
select *
from groep2_13.users

-- UPDATE groep2_13.users
-- set first_name=Bobby, last_name=De Bouwer, email=a@a.be, role=employee, team=ALPHA
-- where userid=4;

ALTER TABLE groep2_13.users
ALTER
COLUMN password TYPE char(128);

hashed passwords:
t: 99f97d455d5d62b24f3a942a1abc3fa8863fc0ce2037f52f09bd785b22b800d4f2e7b2b614cb600ffc2a4fe24679845b24886d69bb776fcfa46e54d188889c6f
a: 1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75

UPDATE groep2_13.users
set password = '99f97d455d5d62b24f3a942a1abc3fa8863fc0ce2037f52f09bd785b22b800d4f2e7b2b614cb600ffc2a4fe24679845b24886d69bb776fcfa46e54d188889c6f'
where userid = 1