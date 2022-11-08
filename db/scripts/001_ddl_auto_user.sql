CREATE TABLE if not exists auto_user(
  id SERIAL PRIMARY KEY,
  username VARCHAR NOT NULL unique ,
  password VARCHAR NOT NULL
);