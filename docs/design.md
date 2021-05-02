# Design document

## About the design

Starting point of the using program is static factory class `QueryFactory` found in package `main/java/factory`. 

I have used [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to build SQL strings: every class is responsible for adding its contribution to the SQL string and then passing it to the next class.

This design also ensures that query builder does not allow user to build invalid SQL, e.g. `QueryFactory.select().column("firstname).alias("f").alias("f").alias() ...`.  

I have used [template method pattern](https://en.wikipedia.org/wiki/Template_method_pattern) to reduce duplicate code in cases, where same functionality differs just a bit in different cases.

Especially in creating list of values, when first value of the list does not need comma before and rest of the values need.

For example in clause `SELECT firstname, lastname, age FROM ...` you need to add first value as `firstname` and the rest of the values as `, lastname`.

That's why you will see lots of this kind of blocks in design:

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-only-columns-class-diagram.jpg)

Class `ColumnTemplate` implements functionality related to selecting columns to query. Classes `FirstColumn`, `Column` and `AliasedColumn` are just special cases.

## Select statement

`SELECT` queries is implemented in package `/main/java/builder/statement/select`. 

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

## Insert statement

`INSERT INTO` queries is implemented in package `/main/java/builder/statement/insert`.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

## Update statement

`UPDATE` queries is implemented in package `/main/java/builder/statement/update`.

`WHERE` clauses used by `UPDATE` is implemented in package `/main/java/builder/clause/where`.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

## Create statements

`CREATE` queries is implemented in package `/main/java/builder/statement/create`.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

## Delete statements

`DELETE` queries is implemented in package `/main/java/builder/statement/delete`.

`WHERE` clauses used by `DELETE` is implemented in package `/main/java/builder/clause/where`.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

## Drop statements

`DROP` queries is implemented in package `/main/java/builder/statement/drop`.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)
