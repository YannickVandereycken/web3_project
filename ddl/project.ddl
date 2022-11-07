-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

CREATE SCHEMA IF NOT EXISTS groep2_13;

CREATE SEQUENCE groep2_13.project_id_seq;

CREATE TABLE groep2_13.projects
(
    projectid   integer     NOT NULL DEFAULT nextval('groep2_13.project_id_seq'::regclass),
    name        char(40)    NOT NULL,
    team        char(40)    NOT NULL,
    start_date  date        NOT NULL,
    end_date    date        NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (projectid)
);


grant all on schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on all SEQUENCES IN SCHEMA groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;
grant all on all tables in schema groep2_13 to local_r0854458, local_r0663460, r0854458, r0663460, local_u0015529, local_u0034562;

set search_path to groep2_13;
select *
from groep2_13.projects