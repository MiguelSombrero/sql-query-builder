# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

Sql query builder is Java-library used for building and executing SQL queries.

**Syntax of the SQL queries is compatible at least with MySQL, but might work with other DBMS too.**

## Main features

- Create `SELECT`, `UPDATE`, `INSERT`, `CREATE`, `DELETE` and `DROP` queries easily
- Execute queries against database and do something with the results
- Supports [user input validation](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/supported.md#validation)
  and [parametrized queries](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/supported.md#parametrized) by default

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
        .where(valueOf("age").greaterThanInteger(18))
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
        <version>2.0.1</version>
    </dependency>

Check the latest version from [GitHub Packages](https://github.com/MiguelSombrero?tab=packages&repo_name=sql-query-builder)

### 3. Install dependency 

    mvn install

## Documentation

[Design document](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/design.md)

[Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md)

[Supported operations](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/supported.md)

## External dependencies

- [Apache Commons DbUtils](https://commons.apache.org/proper/commons-dbutils/index.html) JDBC utility (license: [Apache license 2.0](https://www.apache.org/licenses/LICENSE-2.0))
- [slf4j](http://www.slf4j.org/) logging utility (license: [MIT](http://www.slf4j.org/license.html))

## Known bugs / shortcomings
- In CREATE TABLE user can chain same constraints infinitely (`...column("ID").type("INT").notNull().notNull().notNull() ...`) 
- Missing validation of 'char' type input

## Not yet implemented
- ALTER statements not yet implemented 
- Missing some datatypes (SMALLINT, DECIMAL, NUMERIC, FLOAT, TIME, TEXT, BINARY)
- Character comparison methods in Comparison class

## For developers

### Requirements

- Java 13+ (using enhanced switch statement released in Java 13, should be easy enough to downgrade Java 8)
- Maven 3.6.0+

### Commands

#### Build project

    mvn clean install

#### Run unit tests

    mvn clean test

#### Create release

    mvn gitflow:release

#### Deploy to GitHub packages

GitHub Action for deploying release to GitHub packages triggers when new release is created with `mvn gitflow:release` command.

### Integration tests

Integration tests against live database is implemented in package `src/test/integration/`.
Tests is ignored by default, since they require MySQL database running in docker container (or local machine etc).

#### MySQL tests

You can start official MySQL docker container with command:

    docker run --name mysql_db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=sa -e MYSQL_DATABASE=test_db -d mysql:latest

Integration tests against MySQL database can be found in class `MySQLIntegrationTest`.