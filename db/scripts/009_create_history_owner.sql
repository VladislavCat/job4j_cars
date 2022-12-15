create table if not exists history_owner(
    id serial primary key,
    car_id int not null,
    driver_id int not null,
    startAt date not null,
    endAt date not null
);