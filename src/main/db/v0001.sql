create table "area" (
  id varchar(255) not null,
  name varchar(50) not null,
  longitude numeric,
  latitude numeric,
  primary key (id)
);

create table "statistic" (
  id varchar(255) not null,
  "data_date" timestamp not null,
  area_id varchar(255) not null references area (id),
  primary key (id)
);