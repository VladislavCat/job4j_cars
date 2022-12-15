create table if not exists cars(
    id serial primary key,
    name VARCHAR,
    engine_id int not null references engines(id)
);