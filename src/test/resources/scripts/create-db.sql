CREATE TABLE person (
    ID INT NOT NULL,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age int
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
    id INT,
    person_id INT,
    name VARCHAR(255),
    difficulty INT,
    foreign key (person_id) references person(id)
);