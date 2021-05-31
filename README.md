# sql-SQLQuery-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-SQLQuery-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql SQLQuery builder is Java-library used for building SQL SQLQuery strings more easily. Syntax of the SQLQuery strings is intended to be compatible at least with MySQL.

**Note that**
- This library does not provide any data access methods. It simply builds strings that are valid SQL and can be used as a SQLQuery strings with some other framework/library.
- You should always use parametrized queries in untrusted environments, if your SQL SQLQuery takes user input as parameters. Check out examples of building [parametrized queries](https://github.com/MiguelSombrero/sql-SQLQuery-builder/tree/develop/docs/examples.md#parametrized).

## Examples

Basic SELECT statement:

    String SQLQuery = QueryFactory
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThan(18))
        .build();

    logger.info(SQLQuery)

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

More examples can be found in [examples](https://github.com/MiguelSombrero/sql-SQLQuery-builder/tree/develop/docs/examples.md) document.

## How to use this library

### 1. Add repository information

Add necessary [repository information](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) to your pom-file to access and authenticate to GitHub packages.

### 2. Add Maven dependency

Add Maven dependency to your project:

    <dependency>
        <groupId>com.github.miguelsombrero</groupId>
        <artifactId>sql-SQLQuery-builder</artifactId>
        <version>1.0.4</version>
    </dependency>

Check the latest version from [GitHub Packages](https://github.com/MiguelSombrero?tab=packages&repo_name=sql-SQLQuery-builder)

### 3. Install dependency 

    mvn install

## Documentation

[Design document](https://github.com/MiguelSombrero/sql-SQLQuery-builder/tree/develop/docs/design.md)

[Examples](https://github.com/MiguelSombrero/sql-SQLQuery-builder/tree/develop/docs/examples.md)

[Supported operations](https://github.com/MiguelSombrero/sql-SQLQuery-builder/tree/develop/docs/supported.md)

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