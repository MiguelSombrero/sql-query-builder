# Design document

## About the design

Starting point of the using program is static factory class `QueryFactory` found in package `main/java/factory`. 

I have used [builder pattern](https://en.wikipedia.org/wiki/Builder_pattern) to build SQL strings: every class is responsible for adding its contribution to the SQL string and then passing it to the next class.

Therefore there is a lot of classes with minimun functionality.

This design also ensures that query builder does not allow user to build invalid SQL, e.g. `QueryFactory.select().column("firstname).alias("f").alias("f").alias() ...`.  

I have used [template method pattern](https://en.wikipedia.org/wiki/Template_method_pattern) to reduce duplicate code in cases, where same functionality differs just a bit in different cases.

Especially in creating list of values, when first value of the list does not need comma before and rest of the values need.

For example in clause `SELECT firstname, lastname, age FROM ...` you need to add first value as `firstname` and the rest of the values as `, lastname`.

That's why you will see lots of this kind of blocks in design:

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-only-columns-class-diagram.jpg)

User starts building the SQL string by selecting statement to build in `QueryFactory`.

`select()` method appends `SELECT ` to the query and returns class `FirstColumn`. 

## Select statement

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

## Insert statement

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

## Update statement

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

## Create statements

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

## Delete statements

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)
