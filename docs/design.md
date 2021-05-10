# Design document

Starting point of using library is static factory class `QueryFactory` found in package `main/java/factory`.

## About the design

### Builder pattern

I have used [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to chain classes. 

For example `From.table(String tableName)` method returns `Table` class, and when you are in `Table`, you cannot go back to `From` class.

This design ensures that query builder does not allow user to build invalid SQL, for example call same method infinitely: `QueryFactory.select().column("firstname").alias("f").alias("f").alias("f) ...`.

Therefore, there is relatively large amount of classes with minimum functionality.

### Template method pattern

[Template method](https://en.wikipedia.org/wiki/Template_method_pattern) is used to reduce duplicate code in scenarios, where same functionality differs just a bit in different cases.

Basic example of the scenario is when you are creating list of values, and you need to add comma after first value.

For example in statement `SELECT firstname, lastname, age FROM ...` you need to add first value as `firstname` and the rest of the values with comma as `, lastname` and `, age`.

![Template method_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/template-method-diagram.jpg)

Class `FirstColumn` differs from `Column` and `AliasedColumn`also because you have to choose at least one column before you can call `from()` and `alias(Sting alias)` methods.

## Class diagrams

### Where and Having clause

Classes of `WHERE` and `HAVING` clauses is implemented in package `/main/java/builder/condition`.

Where and having clauses is used as embedded in another SQL statement by calling `where(Condition whereClause)` and `having(Condition havingClause)` methods.

Where clauses is build by its own factory initialized by `WhereClauseFactory`, for example `WhereClauseFactory.valueOf(String operand)`. Having is build by same way by `HavingClauseFactory`.

#### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

Example of where clause: `...from().table("person").where(WhereClauseFactory.valueOf("age").greaterThan(30))`.

Example of having clause: `...from().table("person").groupBy().column("age").where(HavingClauseFactory.avg("age").greaterThan(30))`.

### Select statement

Classes of `SELECT` queries is implemented in package `/main/java/builder/statement/select`.

#### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

#### Supported operations

- SELECT, SELECT DISTINCT, SELECT TOP
    - column, min(column), max(column), avg(column), sum(column)
- FROM
    - table, sub-query
- AS
    - column, table, join table, sub-query
- LEFT JOIN, RIGHT JOIN, INNER JOIN, FULL JOIN
- WHERE
    - condition
    - =, <, >, <=, =>
    - EXISTS, ALL, ANY, IS NULL, BETWEEN, LIKE
    - IN
        - sub-query, list of values
    - AND, OR, NOT
- GROUP BY column
    - HAVING condition
- ORDER BY column
    - ASC, DESC
- LIMIT

### Insert statement

Classes of `INSERT INTO` queries is implemented in package `/main/java/builder/statement/insert`.

#### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

#### Supported operations

- INSERT INTO
    - table (optional columns)
- VALUES
    - (optional values)
    - sub-query

### Update statement

Classes of `UPDATE` queries is implemented in package `/main/java/builder/statement/update`.

#### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

#### Supported operations

- UPDATE
    - table
- SET
    - columns = values
- WHERE
    - condition

### Create statements

Classes of `CREATE` queries is implemented in package `/main/java/builder/statement/create`.

#### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

#### Supported operations

- CREATE
    - table, database, index
- INT, DOUBLE, TIMESTAMP, CHAR, VARCHAR
- NOT NULL, PRIMARY KEY, UNIQUE
- FOREIGN KEY key REFERENCES table(key)

### Delete statements

Classes of `DELETE` queries is implemented in package `/main/java/builder/statement/delete`.

#### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

#### Supported operations

- DELETE FROM
    - table
- WHERE
    - condition

### Drop statements

Classes of `DROP` queries is implemented in package `/main/java/builder/statement/drop`.

#### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)

#### Supported operations

- DROP
    - table, database