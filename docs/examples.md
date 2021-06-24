# Examples

## Table of contents
0. [General](#general)
1. [SELECT statement](#select)
2. [INSERT statement](#insert)
3. [UPDATE statement](#update)
4. [CREATE statement](#create)
5. [DELETE statement](#delete)
6. [DROP statement](#drop)

### <a name="general"></a>General

First, instantiate `SQLQueryBuilder` with datasource to your database:

    SQLQueryBuilder sqlQueryBuilder = new SQLQueryBuilder(datasource);

After that you can start creating and executing queries.

### <a name="select"></a>SELECT statement

Create `SelectQuery` with `SQLQueryBuilder` to execute SQL `SELECT` queries.

#### Basic example

    SelectQuery query = sqlQueryBuilder
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThanInteger(18))
        .build();

    logger.info(query.toString());

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

(line breaks is added for better readability)

Execute `SELECT` query and get the results: 

    List<Row> result = query.execute();

    Row firstRow = result.get(0);

    int age = firstRow.getInteger("age");
    String firstname = firstRow.getString("first");

#### More complex example with joins

You can import static factory methods from `ConditionClauseBuilder` to create `WHERE` and `HAVING` clauses inside query.

    import static builder.clause.ConditionClauseBuilder.*;

    SQLQueryBuilder sqlQueryBuilder = new SQLQueryBuilder(datasource);

    SelectQuery query = sqlQueryBuilder
        .select()
            .column("p.id")
            .column("p.age")
            .column("p.firstname").alias("name")
            .column("c.name").alias("course")
        .from()
            .table("person").alias("p")
        .leftJoin("course").alias("c")
            .on("p.id = c.person_id")
        .where(valueOf("p.age").greaterThanInteger(18)
            .and("p.age").lesserThanInteger(65)
            .and("c.name").isNotNull())
        .orderBy()
            .column("p.age").desc()
        .limit(100)
        .build();

        logger.info(query.toString());

Above code prints out:

    SELECT p.id, p.age, p.firstname AS name, c.name AS course
    FROM person AS p
    LEFT JOIN course AS c ON p.id = c.person_id
    WHERE p.age > 18
    AND p.age < 65
    AND c.name IS NOT NULL
    ORDER BY p.age DESC
    LIMIT 100

#### Example with aggregate functions

    imports ...

    SelectQuery query = sqlQueryBuilder
        .select()
            .column("s.name").alias("school")
            .avg("c.difficulty").alias("avgDifficulty")
        .from()
            .table("school").alias("s")
        .innerJoin("course").alias("c").on("s.id = c.school_id")
        .groupBy()
            .column("school")
        .having(avg("c.difficulty").greaterThanInteger(1))
        .build();

        logger.info(query.toString());

Above code prints out:

    SELECT s.name AS school, AVG(c.difficulty) AS avgDifficulty
    FROM school AS s
    INNER JOIN course AS c ON s.id = c.school_id
    GROUP BY school
    HAVING avgDifficulty > 1

#### Sub-queries can be made by using `SQLQueryBuilder` to build also sub-query

    imports ...

    SelectQuery query = sqlQueryBuilder
        .select()
            .column("*")
        .from()
            .sub(sqlQueryBuilder.
                select()
                    .column("*")
                .from()
                    .table("person")
                .where(valueOf("age).greaterThanInteger(20)))
                .alias("p")
        .build();

        logger.info(query.toString());

This prints out:

    SELECT *
    FROM (
        SELECT *
        FROM person
        WHERE age > 20
    ) AS p

### <a name="insert"></a>INSERT INTO statement

create `InsertQuery` with `SQLQueryBuilder` to execute SQL `INSERT` queries. 

#### Basic example

    imports ...

    InsertQuery query = sqlQueryBuilder
            .insert()
            .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
            .values()
                .setInteger(101)
                .setDate("1980-04-12")
                .setString("Miika")
                .setString("Somero")
                .setInteger(40)
            .build();

        logger.info(query.toString());

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)

#### Insert into select example

    InsertQuery query = sqlQueryBuilder
            .insert()
            .table("person")
            .sub(select()
                    .column("*")
                .from()
                    .table("student")
                .where(valueOf("age").lesserThanInteger(18)))
        .build();

        logger.info(query.toString());

Prints out:

    INSERT INTO person
    SELECT * FROM student
    WHERE age < 18

### <a name="update"></a>UPDATE statement

Create `UpdateQuery` with `SQLQueryBuilder` to execute SQL `UPDATE` queries.

#### Basic example

    UpdateQuery query = sqlQueryBuilder
        .update()
        .table("person")
            .column("age").value(50)
        .where(valueOf("id").equalsInteger(1)
            .or(valueOf("id").equalsInteger(2)))
        .build();

    logger.info(query.toString());

Prints out:

    UPDATE person
    SET age = 50
    WHERE id = 1
    OR id = 2


### <a name="create"></a>CREATE TABLE statement

Create `CreateQuery` with `SQLQueryBuilder` to execute SQL `CREATE` queries.

    CreateQuery query = sqlQueryBuilder
        .create()
        .table("vehicles")
            .column("ID").type("INT").primaryKey()
            .column("name").type("VARCHAR(255)").notNull()
            .column("model").type("VARCHAR(64)")
            .column("manufacturer_id").type("INT")
        .foreignKey("manufacturer_id").references("ID", "manufacturer")
        .build();

    logger.info(query.toString());

Prints out:

    CREATE TABLE vehicles (
        ID INT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        model VARCHAR(64),
        manufacturer_id INT,
        FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID)
    )

CREATE index and CREATE database is also supported, but examples are trivial.

### <a name="delete"></a>DELETE TABLE statement

Create `DeleteQuery` with `SQLQueryBuilder` to execute SQL `DELETE` queries.

#### Basic example

    DeleteQuery query = sqlQueryBuilder 
        .delete()
        .table("address")
        .where(valueOf("city").equalsString("Helsinki")
            .or(valueOf("city").equalsString("Oulu")))
        .build();

    logger.info(query.toString());

Prints out:

    DELETE FROM address
    WHERE city = 'Helsinki'
    OR city = 'Oulu'

### <a name="drop"></a>DROP statement

Create `DropQuery` with `SQLQueryBuilder` to execute SQL `Drop` queries.

#### Basic example

    DeleteQuery query = sqlQueryBuilder 
        .drop()
        .table()
        .name("test_table")
        .build();

    logger.info(query.toString());

Prints out:

    DROP TABLE test_table