# Design document

## About the design

Factory class `QueryFactory` is used to create different queries. Factory creates `Query` object, which represents SQL (or any other) query.

`Query` is passed as a parameter to classes that defines methods for handling query.  

Classes are chained together using [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern).

## `/src/main/java/factory`

Package contains all the factory classes to create queries and clauses.  

Use factory class `QueryFactory` to build SQL queries. Factory classes `WhereClauseFactory` and `HavingClauseFactory` is used to build `WHERE` and `HAVING` clauses, that can be embedded in SQL queries.

See detailed examples in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## `/src/main/java/validation`

Package contains validators for validating user input.

**Note that** these simple validations is not enough for preventing SQL injection type attacks.

If this library is used in untrusted environment, it is highly recommended to use parametrized queries.

Check out examples of building [parametrized queries](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md#parametrized).

## `/src/main/java/builder/condition`

Package where classes of `WHERE` and `HAVING` clauses is implemented.

Where and having clauses is used as embedded in another SQL statement by calling `where(Condition whereClause)` and `having(Condition havingClause)` methods.

### Class diagram

![Condition_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

## `/src/main/java/builder/statement/select`

Package contains classes of `SELECT` queries.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

Create SELECT statement by calling `QueryFactory.select()...` or other select options.

## `/src/main/java/builder/statement/insert`

Package contains classes of `INSERT INTO` queries.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

Create INSERT statement by calling `QueryFactory.inserInto()...`.

## `/src/main/java/builder/statement/update`

Package contains classes of `UPDATE` queries.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

Create UPDATE statement by calling `QueryFactory.update()...`.

## `/src/main/java/builder/statement/create`

Package contains classes of `CREATE` queries.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

Create CREATE statement by calling `QueryFactory.create()...`.

## `/src/main/java/builder/statement/delete`

Package contains classes of `DELETE` queries.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

Create DELETE statement by calling `QueryFactory.deleteFrom()...`.

## `/src/main/java/builder/statement/drop`

Package contains classes of `DROP` queries.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)

Create DROP statement by calling `QueryFactory.drop()...`.
