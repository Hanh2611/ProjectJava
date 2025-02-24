CREATE DATABASE quanlysieuthimini;

SHOW DATABASES;
USE quanlysieuthimini;

CREATE Table users(
    username VARCHAR(50),
    password VARCHAR(50),
    phone VARCHAR(9)
);

drop TABLE users;
SELECT * FROM users;