# Design document

All the classes extends - either directly or by some other class - `SQLQuery`, which represents SQL query and contains methods of appending values to query string.

If class is 'terminal operation', meaning that the query string is valid SQL and can be executed, class extends `TerminalOperation` class (or equivalent `TerminalSelectOperation` etc.).

## `/src/main/java/factory`

Package contains all the factory classes to create queries and clauses.  

Use factory class `QueryFactory` to build SQL queries. Factory classes `WhereClauseFactory` and `HavingClauseFactory` is used to build `WHERE` and `HAVING` clauses, that can be embedded in SQL queries.

See detailed examples in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## `/src/main/java/validation`

Package contains validators for validating user input.

**Note that** these simple validations is not enough for preventing SQL injection type attacks. If this library is used in untrusted environment, it is highly recommended to use parametrized queries.

Check out examples building of parametrized queries in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## `/src/main/java/builder/condition`

Package where classes of `WHERE` and `HAVING` clauses is implemented.

Where and having clauses is used as embedded in another SQL statement by calling `where(Condition whereClause)` and `having(Condition havingClause)` methods.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

## `/src/main/java/builder/statement/select`

Classes of `SELECT` queries is implemented in package `/main/java/builder/statement/select`.

Create SELECT statement by calling `QueryFactory.select()...` or other select options.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

## `/src/main/java/builder/statement/insert`

Classes of `INSERT INTO` queries is implemented in package `/main/java/builder/statement/insert`.

Create INSERT statement by calling `QueryFactory.inserInto()...`.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

## `/src/main/java/builder/statement/update`

Classes of `UPDATE` queries is implemented in package `/main/java/builder/statement/update`.

Create UPDATE statement by calling `QueryFactory.update()...`.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

## `/src/main/java/builder/statement/create`

Classes of `CREATE` queries is implemented in package `/main/java/builder/statement/create`.

Create CREATE statement by calling `QueryFactory.create()...`.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

## `/src/main/java/builder/statement/delete`

Classes of `DELETE` queries is implemented in package `/main/java/builder/statement/delete`.

Create DELETE statement by calling `QueryFactory.deleteFrom()...`.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

## `/src/main/java/builder/statement/drop`

Classes of `DROP` queries is implemented in package `/main/java/builder/statement/drop`.

Create DROP statement by calling `QueryFactory.drop()...`.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)
