CREATE TABLE person (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    birthdate TIMESTAMP,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age int
);
CREATE TABLE student (
    ID INT PRIMARY KEY,
    birthdate TIMESTAMP,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age int
);
CREATE TABLE school (
    ID INT PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE manufacturer (
    ID INT PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE address (
    ID INT PRIMARY KEY,
    person_id INT,
    postal_code INT,
    city VARCHAR(255),
    street VARCHAR(255),
    foreign key (person_id) references person(ID)
);
CREATE TABLE course (
    ID INT PRIMARY KEY,
    person_id INT,
    school_id INT,
    name VARCHAR(255),
    difficulty INT,
    foreign key (person_id) references person(ID),
    foreign key (school_id) references school(ID)
);
CREATE TABLE all_types (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    rating SMALLINT,
    hash BIGINT,
    age DOUBLE,
    price DECIMAL(8,2),
    taxes NUMERIC(4,2),
    newdate DATE,
    clock TIME,
    created TIMESTAMP,
    active BOOLEAN,
    country CHAR,
    model VARCHAR(32),
    brand VARCHAR(64),
    description VARCHAR(255),
    contract BLOB
);