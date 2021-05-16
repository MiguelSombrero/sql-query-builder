# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql query builder is Java-library used for building SQL query strings more easily. Syntax of the query strings is intended to be compatible at least with MySQL.

**Note** that this is not an ORM and does not provide any data access methods. This library simply builds strings that are valid SQL and can be used as a query strings by some other framework/library.   

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

## How to use this library

### 1. Add repository information

Add necessary [repository information](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) to your pom-file to access and authenticate to GitHub packages.

### 2. Add Maven dependency

Add Maven dependency to your project:

    <dependency>
        <groupId>com.github.miguelsombrero</groupId>
        <artifactId>sql-query-builder</artifactId>
        <version>1.0.4</version>
    </dependency>

Check the latest version from [GitHub Packages](https://github.com/MiguelSombrero?tab=packages&repo_name=sql-query-builder)

### 3. Install dependency 

    mvn install

### Use

Starting point of using library is static factory class `QueryFactory` found in package `main/java/factory`.

## Documentation

[Design documents](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/design.md)

[Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md)

[Supported operations](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/supported.md)

## Features scheduled on next release

### General
- Javadoc
- Parametrized queries (WHERE name = ?)

### CREATE
- Constraints 'default' and 'check'
- Foreign key constraints for composite keys

Something else missing?

## Known issues
- In CREATE TABLE user can chain same constraints infinitely (`...column("ID").type(DataType.INT).notNull().notNull().notNull() ...`) 

## For developers

### Requirements

- Java 8+
- Maven 3.6.0+

### Commands

#### Build project

    mvn clean install

#### Run tests

    mvn clean test

#### Create release

    mvn gitflow:release

#### Deploy to GitHub packages

GitHub Action for deploying release to GitHub packages triggers when new release is created with `mvn gitflow:release` command.