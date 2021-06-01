# Design document

## Table of contents
1. [About the design](#about)
2. [Factory classes](#factory)
3. [User input validation](#validation)
4. [WHERE and HAVING](#condition)
5. [SELECT statement](#select)
6. [INSERT statement](#insert)
7. [UPDATE statement](#update)
8. [CREATE statement](#create)
9. [DELETE statement](#delete)
10. [DROP statement](#drop)

## <a name="about"></a>About the design

Factory class `QueryFactory` is used to create different queries. Factory creates `Query` object, which represents SQL (or any other) query.

`Query` is passed as a parameter to classes that defines methods for handling query.  

Classes are chained together using [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern).

## <a name="factory"></a>Factory classes

Package `/src/main/java/factory` contains all the factory classes to create queries and clauses.  

Use factory class `QueryFactory` to build SQL queries. Factory classes `WhereClauseFactory` and `HavingClauseFactory` is used to build `WHERE` and `HAVING` clauses, that can be embedded in SQL queries.

See detailed examples in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## <a name="validation"></a>User input validation

Package `/src/main/java/validation` contains validators for validating user input.

**Note that** these validations is not enough for preventing SQL injection type attacks.

If this library is used in untrusted environment, it is highly recommended to use parametrized queries.

Check out examples of building [parametrized queries](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md#parametrized).

## <a name="condition"></a>WHERE and HAVING conditions

Package `/src/main/java/builder/condition` contains classes of `WHERE` and `HAVING` clauses.

Where and having clauses is used as embedded in another SQL statement by calling `where(Condition whereClause)` and `having(Condition havingClause)` methods.

### Class diagram

![Condition_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

## <a name="select">SELECT statement

Package `/src/main/java/builder/statement/select` contains classes of `SELECT` queries.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

Create SELECT statement by calling `QueryFactory.select()...` or other select options.

## <a name="insert">INSERT statement

Package `/src/main/java/builder/statement/insert` contains classes of `INSERT INTO` queries.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

Create INSERT statement by calling `QueryFactory.inserInto()...`.

## <a name="update">UPDATE statement

Package `/src/main/java/builder/statement/update` contains classes of `UPDATE` queries.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

Create UPDATE statement by calling `QueryFactory.update()...`.

## <a name="create">CREATE statement

Package `/src/main/java/builder/statement/create` contains classes of `CREATE` queries.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

Create CREATE statement by calling `QueryFactory.create()...`.

## <a name="delete">DELETE statement

Package `/src/main/java/builder/statement/delete` contains classes of `DELETE` queries.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

Create DELETE statement by calling `QueryFactory.deleteFrom()...`.

## <a name="drop">DROP statement

Package `/src/main/java/builder/statement/drop` ontains classes of `DROP` queries.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)

Create DROP statement by calling `QueryFactory.drop()...`.
