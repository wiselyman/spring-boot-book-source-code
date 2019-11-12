create table if not exists person (
  id serial primary key,
  name varchar(255) not null,
  age int4 not null
);