CREATE TABLE IF NOT EXISTS drivers(
    id serial primary key,
    name VARCHAR,
    user_id int references auto_user(id)
);