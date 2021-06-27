# Design document

## Table of contents
1. [About the design](#about)
2. [Query and Statement](#query)
3. [WHERE and HAVING](#condition)
4. [SELECT statement](#select)
5. [INSERT statement](#insert)
6. [UPDATE statement](#update)
7. [CREATE statement](#create)
8. [DELETE statement](#delete)
9. [DROP statement](#drop)

## <a name="about"></a>About the design

This document gives an overview of design of the sql-query-builder.

`SQLQueryBuilder` implementation uses [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to chain classes.
This design enforces correct SQL syntax when building queries.

**Notice that class diagrams below is missing some classes, methods and relations**, for the sake of simplicity.  

## <a name="query"></a>Query and Statement

Package `/src/main/java/query` contains classes of `Query` and `Statement` objects. Interface `Statement` represent SQL statement, which holds query string and query parameters.
Interface `Query` and its implementing classes represents SQL query which can be executed against database.

### Class diagram

![Query_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/query-class-diagram.jpg)

## <a name="condition"></a>WHERE and HAVING conditions

Package `/src/main/java/builder/statement` contains classes of building `WHERE` and `HAVING` clauses.

Where and having clauses are used embedded in another SQL query (e.g. SelectQuery) and cannot be executed at its own.

### Class diagram

![Condition_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

## <a name="select">SELECT statement

Package `/src/main/java/builder/statement/select` contains classes of `SELECT` queries.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

Create SELECT statement by calling `SQLQueryBuilder.select()...` or other select options.

## <a name="insert">INSERT statement

Package `/src/main/java/builder/statement/insert` contains classes of `INSERT INTO` queries.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

Create INSERT statement by calling `SQLQueryBuilder.inserInto()...`.

## <a name="update">UPDATE statement

Package `/src/main/java/builder/statement/update` contains classes of `UPDATE` queries.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

Create UPDATE statement by calling `SQLQueryBuilder.update()...`.

## <a name="create">CREATE statement

Package `/src/main/java/builder/statement/create` contains classes of `CREATE` queries.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

Create CREATE statement by calling `SQLQueryBuilder.create()...`.

## <a name="delete">DELETE statement

Package `/src/main/java/builder/statement/delete` contains classes of `DELETE` queries.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

Create DELETE statement by calling `SQLQueryBuilder.deleteFrom()...`.

## <a name="drop">DROP statement

Package `/src/main/java/builder/statement/drop` contains classes of `DROP` queries.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)

Create DROP statement by calling `SQLQueryBuilder.drop()...`.
