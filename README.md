# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql query builder is Java-library used for building and executing SQL queries.

**Syntax of the SQL queries is compatible at least with MySQL, but might work with other DBMS too.**

## Main features

- Create `SELECT`, `UPDATE`, `INSERT`, `CREATE`, `DELETE` and `DROP` queries easily
- Execute queries against database and do something with the results
- Supports user input validation and parametrized queries

## Examples

To use sql-query-builder, you need to instantiate `SQLQueryBuilder` class with [Datasource](https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html) object to connect your database.

    SQLQueryBuilder sqlQueryBuilder = new SQLQueryBuilder(datasource);

After this you can start creating queries:

    SelectQuery query = sqlQueryBuilder
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThan(18))
        .build();

Query object can be used as a query string with some other database framework:

    String queryString = query.toString();

    logger.info(queryString);

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

Or Query object can be used to execute query directly:

    List<Row> result = query.execute();

`SELECT` query results list of `Row` objects, which represents database rows. To access rows and data:

    Row firstRow = result.get(0);

    int age = firstRow.getInteger("age");
    String firstname = firstRow.getString("first");

More examples can be found in [examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## How to use this library

### 1. Add repository information

Add necessary [repository information](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) to your pom-file to access and authenticate to GitHub packages.

### 2. Add Maven dependency

Add Maven dependency to your project:

    <dependency>
        <groupId>com.github.miguelsombrero</groupId>
        <artifactId>sql-query-builder</artifactId>
        <version>1.0.5</version>
    </dependency>

Check the latest version from [GitHub Packages](https://github.com/MiguelSombrero?tab=packages&repo_name=sql-query-builder)

### 3. Install dependency 

    mvn install

## Documentation

[Design document](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/design.md)

[Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md)

[Supported operations](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/supported.md)

## Known issues
- In CREATE TABLE user can chain same constraints infinitely (`...column("ID").type(DataType.INT).notNull().notNull().notNull() ...`) 

## For developers

### Requirements

- Java 13+ (using enhanced switch statement released in Java 13)
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