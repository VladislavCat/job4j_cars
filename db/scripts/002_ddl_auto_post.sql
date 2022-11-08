create table if not exists auto_post(
    id serial primary key,
    auto_name varchar not null,
    description text not null,
    created date not null,
    auto_user_id int not null references auto_user(id)
);