CREATE TABLE Area (
  dtype varchar(20) not null,
  id SERIAL,
  latitude double precision,
  longitude double precision,
  name varchar(100) not null
);