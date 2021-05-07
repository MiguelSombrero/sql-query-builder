# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql query builder is Java-library used to build SQL query strings more easily.

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

### Use

Starting point of using library is static factory class `QueryFactory` found in package `main/java/factory`.

## Examples

Basic SELECT statement:

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

More examples can be found in [examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## Documentation

[Design documents](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/design.md)

[Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md)

## Not yet implemented

### SELECT
- Doubles and other datatypes in WHERE clauses (greaterThan(4.5), ...)
- Better Like operator (giving patterns without "%" etc. symbols)
- Better HAVING clause (no need for giving condition by string)
- Any and All operators (ANY, ALL)
- Select into statement (SELECT INTO)
- Case statements (CASE - WHEN, THEN)
- Exists operator (EXISTS)
- Union operator (UNION)

### DELETE
- Delete from multiple tables with joins (DELETE T1, T2, FROM T1 ... JOIN ...)

### CREATE
- Create table constraints (foreign key, default, check, on delete, ...)
- Create table AS clauses (CREATE table x AS, ...)

And propably many more special cases ...

## Known issues

- You can pass other than `SELECT` statements in sub-select (`sub(Builder query)`) methods.
- In CREATE TABLE statements you can chain constraints infinitely (e.g. CREATE TABLE person (id INT NOT NULL NOT NULL NOT NULL ...))
- In UPDATE table statement you can terminate query too soon (QueryFactory.update().table("person").build())
