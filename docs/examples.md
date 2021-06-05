# Examples

## Table of contents
1. [SELECT statement](#select)
2. [INSERT statement](#insert)
3. [UPDATE statement](#update)
4. [CREATE statement](#create)
5. [DELETE statement](#delete)
6. [DROP statement](#drop)
7. [Parametrized queries](#parametrized)

### <a name="select"></a>SELECT statement

#### Basic example

    String SQLQuery = QueryFactory
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThan(18))
        .build();

    logger.info(SQLQuery)

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

(line breaks are added for better readability)

#### More complex example with joins

You can import static factory methods for queries to look more natural language like.

    import static query.QueryFactory.*;
    import static builder.clause.WhereClauseFactory.*;

    String SQLQuery = select()
            .column("p.id")
            .column("p.age")
            .column("p.firstname").alias("name")
            .column("c.name").alias("course")
        .from()
            .table("person").alias("p")
        .leftJoin("course").alias("c")
            .on("p.id = c.person_id")
        .where(valueOf("p.age").greaterThan(18)
            .and("p.age").lesserThan(65)
            .and("c.name").isNotNull())
        .orderBy()
            .column("p.age").desc()
        .limit(100)
        .build();

        logger.info(SQLQuery);

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

    ...
    import static builder.clause.HavingClauseFactory.*;

    String SQLQuery = select()
            .column("s.name").alias("school")
            .avg("c.difficulty").alias("avgDifficulty")
        .from()
            .table("school").alias("s")
        .innerJoin("course").alias("c").on("s.id = c.school_id")
        .groupBy()
            .column("school")
        .having(avg("c.difficulty").greaterThan(1))
        .build();

        logger.info(SQLQuery);

Above code prints out:

    SELECT s.name AS school, AVG(c.difficulty) AS avgDifficulty
    FROM school AS s
    INNER JOIN course AS c ON s.id = c.school_id
    GROUP BY school
    HAVING avgDifficulty > 1

#### Sub-queries can be made by using `QueryFactory` to build sub-SQLQuery

    String SQLQuery = select()
            .column("*")
        .from()
            .sub(select()
                    .column("*")
                .from()
                    .table("person")
                .where(valueOf("age).greaterThan(20)))
                .alias("p")
        .build();

This prints out:

    SELECT *
    FROM (
        SELECT *
        FROM person
        WHERE age > 20
    ) AS p

### <a name="insert"></a>INSERT INTO statement

#### Basic example

    String SQLQuery = insertInto()
            .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
            .values()
                .value(101)
                .value("1980-04-12")
                .value("Miika")
                .value("Somero")
                .value(40)
            .build();

        logger.info(SQLQuery);

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)

#### Insert into select example

    String SQLQuery = insertInto()
            .table("person")
            .sub(select()
                    .column("*")
                .from()
                    .table("student")
                .where(valueOf("age").lesserThan(18)))
        .build();

        logger.info(SQLQuery);

Prints out:

    INSERT INTO person
    SELECT * FROM student
    WHERE age < 18

### <a name="update"></a>UPDATE statement

#### Basic example

    String SQLQuery = update()
        .table("person")
            .column("age").value(50)
        .where(valueOf("id").equals(1)
            .or(valueOf("id").equals(2)))
        .build();

    logger.info(SQLQuery);

Prints out:

    UPDATE person
    SET age = 50
    WHERE id = 1
    OR id = 2


### <a name="create"></a>CREATE TABLE statement

    String SQLQuery = create()
        .table("vehicles")
            .column("ID").type(DataType.INT).primaryKey()
            .column("name").type(DataType.VARCHAR_255).notNull()
            .column("model").type(DataType.VARCHAR_64)
            .column("manufacturer_id").type(DataType.INT)
        .foreignKey("manufacturer_id").references("ID", "manufacturer")
        .build();

    logger.info(SQLQuery);

Prints out:

    CREATE TABLE vehicles (
        ID INT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        model VARCHAR(64),
        manufacturer_id INT,
        FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID)
    )

DELETE index and DELETE testutils are also supported, but examples are trivial.

### <a name="delete"></a>DELETE TABLE statement

#### Basic example

    String SQLQuery = deleteFrom()
        .table("address")
        .where(valueOf("city").equals("Helsinki)
            .or(valueOf("city").equals("Oulu")))
        .build();

    logger.info(SQLQuery);

Prints out:

    DELETE FROM address
    WHERE city = 'Helsinki'
    OR city = 'Oulu'

### <a name="drop"></a>DROP statement

#### Basic example

    String SQLQuery = drop()
        .table("test_table")
        .build();

    logger.info(SQLQuery);

Prints out:

    DROP TABLE test_table

### <a name="parametrized"></a>Parametrized queries

You should always use parametrized queries in untrusted environments, if your SQL SQLQuery takes user input as parameters.

You can create parametrized `INSERT`, `UPDATE`, `WHERE` and `HAVING` statements to use with prepared statements.

#### INSERT statement

    String SQLQuery = insertInto()
        .table("person")
        .columns("id", "birthdate", "firstname", "lastname", "age")
        .values()
            .value("?)
            .value("?")
            .value("?")
            .value("?")
            .value("?")
        .build();

        logger.info(SQLQuery);

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (?, ?, ?, ?, ?)

Now you can use this SQLQuery with prepared statement:

    Connection conn = DriverManager.getConnection(path, user, password);  

    PreparedStatement stmt = conn.prepareStatement(SQLQuery);  
    stmt.setInt(1, 1);  
    stmt.setString(2, "2021-05-20");  
    stmt.setString(3, "Miika");
    stmt.setString(4, "Somero");
    stmt.setInt(5, 40);

#### UPDATE statement

    String SQLQuery = update()
        .table("person")
        .column("firstname").value("?")
        .column("lastname").value("?")
        .column("age").value("?")
        .build();

        logger.info(SQLQuery);

Prints out:

    UPDATE person
    SET firstname = ?, lastname = ?, age = ?

#### WHERE clause in SELECT statement

    String SQLQuery = select()
        .column("firstname")
        .from()
            .table("person");
        .where(valueOf("firstname").isIn("?", "?", "?"))
        .build();

        logger.info(SQLQuery);

Prints out:

    SELECT firstname
    FROM person
    WHERE firstname IN (?, ?, ?)