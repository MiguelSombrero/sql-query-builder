# Design document

## Table of contents
1. [About the design](#about)
2. [WHERE and HAVING](#condition)
3. [SELECT statement](#select)
4. [INSERT statement](#insert)
5. [UPDATE statement](#update)
6. [CREATE statement](#create)
7. [DELETE statement](#delete)
8. [DROP statement](#drop)

## <a name="about"></a>About the design

This document gives an overview of design of the sql-query-builder.

Query builder `SQLQueryBuilder` is using [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to chain classes when building the queries.
This design is used to enforce correct SQL syntax when building queries.

## <a name="condition"></a>WHERE and HAVING conditions

Package `/src/main/java/builder/clause` contains classes of building `WHERE` and `HAVING` clauses.

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
