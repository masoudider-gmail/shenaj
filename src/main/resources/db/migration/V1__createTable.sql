CREATE TABLE app_user(
id BIGSERIAL PRIMARY KEY ,
username varchar NOT NULL,
password varchar NOT NULL ,
role varchar NOT NULL,
unique (username)
);

CREATE TABLE ticket(
id BIGSERIAL PRIMARY KEY ,
ticket varchar NOT NULL,
answer varchar ,
user_id int NOT NULL,
FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);