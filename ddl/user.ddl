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
    userid     integer  NOT NULL DEFAULT nextval('groep2_13.user_id_seq'::regclass),
    first_name char(40) NOT NULL,
    last_name  char(40) NOT NULL,
    email      char(40) NOT NULL,
    role       char(40) NOT NULL,
    team       char(40) NOT NULL,
    password   char(40) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (userid)
);

CREATE SEQUENCE groep2_13.workorder_id_seq;

CREATE TABLE groep2_13.workorders
(
    workorderid integer   NOT NULL DEFAULT nextval('groep2_13.workorder_id_seq'::regclass),
    name        char(40)  NOT NULL,
    team        char(40)  NOT NULL,
    date        date      NOT NULL,
    start_time  time      NOT NULL,
    end_time    time      NOT NULL,
    description char(100) NOT NULL,
    CONSTRAINT pk_workorder PRIMARY KEY (workorderid)
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