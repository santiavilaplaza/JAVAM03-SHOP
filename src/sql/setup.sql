DROP DATABASE IF EXISTS shop;

CREATE DATABASE shop;

USE shop;

CREATE TABLE employee (
    employeeId INT PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    password VARCHAR(255)
);

INSERT INTO employee (employeeId, name, password)
VALUES (123, "Santi", "test1"), (456, "Jose", "test2"), 
(789, "Vicente", "test3"), (111, "Gabriel", "gabri");