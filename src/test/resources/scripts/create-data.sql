INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (1, '1980-02-28T21:00:00.000', 'Miika', 'Somero', 39);
INSERT INTO school (id, name) VALUES (1, 'Helsingin yliopisto');
INSERT INTO address (id, person_id, postal_code, city, street) VALUES (1, 1, 86400, 'Vihanti', 'Testitie');
INSERT INTO course (id, person_id, school_id, name, difficulty) VALUES (1, 1, 1, 'Modern React', 4);

