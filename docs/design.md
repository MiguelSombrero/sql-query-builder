# Design document

## About the design

Starting point of the using program is static factory class `QueryFactory` found in package `main/java/factory`. 

I have tried to use [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to chain classes together. 
This design also ensures that query builder does not allow user to build invalid SQL, e.g. `QueryFactory.select().column("firstname).alias("f").alias("f").alias() ...`.
Therefore, there is relatively large amount of classes with minimum functionality.

Arrows ----> in class diagrams represents direction the flow goes.
For example `FirstTable` creates `Table`, but when you are in `Table`, you cannot go back to `FirstTable`.

## Select statement

`SELECT` queries is implemented in package `/main/java/builder/statement/select`. 

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)


## Where clause

`WHERE` clause is implemented in package `/main/java/builder/clause/where`.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

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
