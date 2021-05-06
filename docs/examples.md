# Examples

## SELECT

**Basic example:**

    String query = QueryFactory
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThan(18))
        .build();

    logger.info(query)

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

**More complex example with joins:**

    String query = QueryFactory
        .select()
            .column("p.id")
            .column("p.age")
            .column("p.firstname").alias("name")
            .column("c.name").alias("course")
        .from()
            .table("person").alias("p")
        .leftJoin("course").alias("c")
            .on("p.id = c.person_id")
        .where(valueOf("p.age").greaterThan(18)
            .and("p.age").lesserThan(65)
            .and("c.name").isNotNull())
        .orderBy()
            .column("p.age").desc()
        .limit(100)
        .build();

        logger.info(query);

Above code prints out:

    SELECT p.id, p.age, p.firstname AS name, c.name AS course
    FROM person AS p
    LEFT JOIN course AS c ON p.id = c.person_id
    WHERE p.age > 18
    AND p.age < 65
    AND c.name IS NOT NULL
    ORDER BY p.age DESC
    LIMIT 100

**Example with aggregate functions:**

    String query = QueryFactory
        .select()
            .column("s.name").alias("school")
            .avg("c.difficulty").alias("avgDifficulty")
        .from()
            .table("school").alias("s")
        .innerJoin("course").alias("c").on("s.id = c.school_id")
        .groupBy()
            .column("school")
        .having("avgDifficulty > 1")
        .build();

        logger.info(query);

Above code prints out:

    SELECT s.name AS school, AVG(c.difficulty) AS avgDifficulty
    FROM school AS s
    INNER JOIN course AS c ON s.id = c.school_id
    GROUP BY school
    HAVING avgDifficulty > 1

**Sub-queries can be made by using `QueryFactory` to build sub-query:**

    String query = QueryFactory
        .select()
            .column("*")
        .from()
            .sub(QueryFactory
                .select()
                    .column("*")
                .from()
                    .table("person")
                .where(valueOf("age).greaterThan(20)))
                .alias("p")
        .build();

This prints out:

    SELECT *
    FROM (
        SELECT *
        FROM person
        WHERE age > 20
    ) AS p

## INSERT INTO

**Basic example:**

    String query = QueryFactory
        .insertInto()
            .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
            .values()
                .value(101)
                .value("1980-04-12")
                .value("Miika")
                .value("Somero")
                .value(40)
            .build();

        logger.info(query);

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)

**Insert into select example:**

    String query = QueryFactory
        .insertInto()
            .table("person")
            .sub(QueryFactory
                .select()
                    .column("*")
                .from()
                    .table("student")
                .where(valueOf("age").lesserThan(18)))
        .build();

        logger.info(query);

Prints out:

    INSERT INTO person
    SELECT * FROM student
    WHERE age < 18

## UPDATE

## CREATE

## DELETE

## DROP