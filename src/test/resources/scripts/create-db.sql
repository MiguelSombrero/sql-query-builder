CREATE TABLE person (
    ID INT NOT NULL,
    birthdate TIMESTAMP,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age int
);

CREATE TABLE school (
    id INT NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE address (
    ID INT NOT NULL,
    person_id INT,
    postal_code INT,
    city VARCHAR(255),
    street VARCHAR(255),
    foreign key (person_id) references person(id)
);

CREATE TABLE course (
    id INT NOT NULL,
    person_id INT,
    school_id INT,
    name VARCHAR(255),
    difficulty INT,
    foreign key (person_id) references person(id),
    foreign key (school_id) references school(id)
);