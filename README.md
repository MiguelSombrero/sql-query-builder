# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql query builder is Java-library used to build SQL query strings more easily.

## Examples

### SELECT

Basic example:

    String query = QueryFactory
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from("person")
        .where("age").greaterThan(18)
        .build();

    logger.info(query)
    
Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

More complex example with joins:

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
                .where("p.age")
                    .greaterThan(18)
                .and("p.age")
                    .lesserThan(65)
                .and("c.name")
                    .isNotNull()
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

Example with aggregate functions:

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

Sub-queries can be made by using `QueryFactory` to build sub-query:

    String query = QueryFactory
                .select()
                    .column("*")
                .from()
                    .sub(QueryFactory
                            .select()
                                .column("*")
                            .from()
                                .table("person")
                            .where("age").greaterThan(20)
                            .build()
                    )
                .alias("p")
                .build();

This prints out:

    SELECT *
    FROM (
        SELECT *
        FROM person
        WHERE age > 20
    ) AS p

## How to use this library

### Add Maven dependency

Add Maven dependency to your project:

    <dependency>
        <groupId>com.github.miguelsombrero</groupId>
        <artifactId>sql-query-builder</artifactId>
        <version>{version}-SNAPSHOT</version>
    </dependency>

Check the latest version from [GitHub](https://github.com/MiguelSombrero/sql-query-builder) 

### Install dependency 

    mvn install

## Documentation

[Design documents](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/design.md)

## Not yet implemented

### SELECT
- Sub-queries with where clauses (WHERE id IN (SELECT ...))
- Doubles and other datatypes in WHERE clauses (greaterThan(4.5), ...)
- Better Like operator (giving patterns without "%" etc. symbols)
- Better HAVING clause (no need for giving condition by string)
- Any and All operators (ANY, ALL)
- Select into statement (SELECT INTO)
- Case statements (CASE - WHEN, THEN)
- Sub SELECT and conditional clauses
- Exists operator (EXISTS)
- Union operator (UNION)
- In select operator (IN (SELECT), ...)

### INSERT
- Sub-queries with where clauses (WHERE id IN (SELECT ...))

### UPDATE
- Sub-queries with where clauses (WHERE id IN (SELECT ...))

### DELETE
- Delete from multiple tables with joins (DELETE T1, T2, FROM T1 ... JOIN ...)
- Sub-queries with where clauses (WHERE id IN (SELECT ...))

### CREATE
- Create table constraints (foreign key, default, check, on delete, ...)
- Create table AS clauses (CREATE table x AS, ...)

And propably many more special cases ...

## Known issues

- In CREATE TABLE statements you can chain constraints infinitely (e.g. CREATE TABLE person (id INT NOT NULL NOT NULL NOT NULL ...))
- In UPDATE table statement you can terminate query too soon (QueryFactory.update().table("person").build())
