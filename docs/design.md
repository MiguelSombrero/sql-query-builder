# Design document

Starting point of using library is static factory class `QueryFactory` found in package `main/java/factory`.

## About the design

### Builder pattern

I have used [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to chain classes. 

For example `From.table(String tableName)` method returns `Table` class, and when you are in `Table`, you cannot go back to `From` class.

This design ensures that query builder does not allow user to build invalid SQL, for example call same method infinitely: `QueryFactory.select().column("firstname).alias("f").alias("f").alias("f) ...`.

Therefore, there is relatively large amount of classes with minimum functionality.

### Template method pattern

[Template method](https://en.wikipedia.org/wiki/Template_method_pattern) is used to reduce duplicate code in scenarios, where same functionality differs just a bit in different cases.

Basic example of the scenario is when you are creating list of values, and you need to add comma after first value.

For example in statement `SELECT firstname, lastname, age FROM ...` you need to add first value as `firstname` and the rest of the values with comma as `, lastname`.

![Template method_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/template-method-diagram.jpg)

## Class diagrams

### Where clause

Classes of `WHERE` clause is implemented in package `/main/java/builder/clause/where`.

Where clause is used embedded in another SQL statement by `where(Conjunction clause)` method.

Where clauses is build by its own factory initialized by `WhereClauseFactory`, for example `WhereClauseFactory.valueOf(String operand)`.

#### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/where-class-diagram.jpg)

For the convenience, showing in diagram only one overload of every method of `Comparison` class.

Where clause and its builder is used in all the statements that are using `WHERE` conditions.

For Example `...from().table("person").where(WhereClauseFactory.valueOf("age").greaterThan(30))`.

### Select statement

Classes of `SELECT` queries is implemented in package `/main/java/builder/statement/select`.

#### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

### Insert statement

Classes of `INSERT INTO` queries is implemented in package `/main/java/builder/statement/insert`.

#### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

### Update statement

Classes of `UPDATE` queries is implemented in package `/main/java/builder/statement/update`.

#### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

### Create statements

Classes of `CREATE` queries is implemented in package `/main/java/builder/statement/create`.

#### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

### Delete statements

Classes of `DELETE` queries is implemented in package `/main/java/builder/statement/delete`.

#### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

### Drop statements

Classes of `DROP` queries is implemented in package `/main/java/builder/statement/drop`.

#### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)
