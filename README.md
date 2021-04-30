# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

**This app is under development**

Sql query builder is Java-library to build SQL query strings more easily.

## Examples

### SELECT

    String query = QueryFactory
        .select()
        .field("firstname").alias("first")
        .field("lastname").alias("last")
        .field("age")
        .from("person")
        .where("age").greaterThan(18)
        .build();

    logger.info(query)
    
Above code prints out:

    "SELECT firstname AS first, lastname AS last, age FROM person WHERE age > 18;"
    
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

In order to be implemented:
- Create table constraints (NOT NULL, foreign key, ...)
- Drop clause (DROP DATABASE, DROP TABLE, ...) 
- Update clauses (UPDATE, SET, ...)
- Delete clauses (DELETE, ...)
- Better Like operator (giving patterns without "%" etc. symbols)
- Better HAVING clause (no need for giving condition by string)
- In operator (IN (...), IN (SELECT), ...)
- Any and All operators (ANY, ALL)
- Select into statement (SELECT INTO)
- Case statements (CASE - WHEN, THEN)
- Sub SELECT and conditional clauses
- Exists operator (EXISTS)
- Create table AS clauses (CREATE table x AS, ...)

And propably many more special cases ...

## Known issues

- In CREATE TABLE statements you can chain constraints infinitely (e.g. CREATE TABLE person (id INT NOT NULL NOT NULL NOT NULL ...))
