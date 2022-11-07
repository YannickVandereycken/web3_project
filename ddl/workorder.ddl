-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

CREATE SCHEMA IF NOT EXISTS groep2_13;

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


INSERT INTO groep2_13.workorders(workorderid, name, team, date, start_time, end_time, description)
VALUES (1, 'Bob', 'ALPHA', '2006-04-03', '06:00', '12:00', 'dont delete this is for testing');

set search_path to groep2_13;
select *
from groep2_13.workorders