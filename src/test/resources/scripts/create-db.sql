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