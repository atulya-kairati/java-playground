CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    mail TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    age INT NOT NULL,
    gender TEXT NOT NULL
);

--ALTER TABLE customer
--ADD CONSTRAINT constraint_unique_mail UNIQUE (mail);
