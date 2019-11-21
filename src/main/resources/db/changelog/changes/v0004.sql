create table test_profile (
    id bigserial,
    primary key (id)
);

create table request (
   id bigserial,
   test_profile_id bigint REFERENCES test_profile (id),
   url varchar(300),
   body text,
   method varchar(10),
   perc integer,
   primary key (id)
)
