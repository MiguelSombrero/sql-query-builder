INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (1, '1980-02-28T21:00:00.000', 'Miika', 'Somero', 39);
INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (2, '1970-02-28T21:00:00.000', 'Jukka', 'Jukkanen', 30);
INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (3, '1990-02-28T21:00:00.000', 'Maija', 'Kultanen', 35);

INSERT INTO student (id, birthdate, firstname, lastname, age) VALUES (4, '1990-02-28T21:00:00.000', 'Liisa', 'Testinen', 19);
INSERT INTO student (id, birthdate, firstname, lastname, age) VALUES (5, '2001-02-28T21:00:00.000', 'Jaska', 'Jokunen', 17);

INSERT INTO school (id, name) VALUES (6, 'Helsingin yliopisto');

INSERT INTO address (id, person_id, postal_code, city, street) VALUES (7, 1, 86400, 'Vihanti', 'Testitie');

INSERT INTO course (id, person_id, school_id, name, difficulty) VALUES (8, 1, 6, 'Modern React', 4);

