INSERT INTO person (id, firstname, lastname, age) VALUES (1, 'Miika', 'Somero', 39);
INSERT INTO person (id, firstname, lastname, age) VALUES (2, 'Jukka', 'Karihtala', 62);
INSERT INTO person (id, firstname, lastname, age) VALUES (3, 'Risto', 'Äkkälä', 19);

INSERT INTO address (id, person_id, postal_code, city, street) VALUES (1, 1, 86400, 'Vihanti', 'Testitie');

INSERT INTO course (id, person_id, name, difficulty) VALUES (1, 1, 'Modern React', 4);

