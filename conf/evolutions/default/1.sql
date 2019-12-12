-- !Ups

CREATE TABLE public.users (
    id BIGSERIAL NOT NULL,
    email varchar(255) NOT NULL,
    fullname varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

insert into users values (1, 'test@gmail.com', 'testUser');

-- !Downs

DROP TABLE users;
