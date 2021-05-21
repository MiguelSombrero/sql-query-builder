# Examples

## SELECT statement

### Basic example

    String query = QueryFactory
        .select()
            .column("firstname").alias("first")
            .column("lastname").alias("last")
            .column("age")
        .from()
            .table("person")
        .where(valueOf("age").greaterThan(18))
        .build();

    logger.info(query)

Above code prints out:

    SELECT firstname AS first, lastname AS last, age
    FROM person
    WHERE age > 18

(line breaks are added for better readability)

### More complex example with joins

You can import static factory methods for queries to look more natural language like.

    import static factory.QueryFactory.*;
    import static factory.WhereClauseFactory.*;

    String query = select()
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

        logger.info(query);

Above code prints out:

    SELECT p.id, p.age, p.firstname AS name, c.name AS course
    FROM person AS p
    LEFT JOIN course AS c ON p.id = c.person_id
    WHERE p.age > 18
    AND p.age < 65
    AND c.name IS NOT NULL
    ORDER BY p.age DESC
    LIMIT 100

### Example with aggregate functions

    ...
    import static factory.HavingClauseFactory.*;

    String query = select()
            .column("s.name").alias("school")
            .avg("c.difficulty").alias("avgDifficulty")
        .from()
            .table("school").alias("s")
        .innerJoin("course").alias("c").on("s.id = c.school_id")
        .groupBy()
            .column("school")
        .having(avg("c.difficulty").greaterThan(1))
        .build();

        logger.info(query);

Above code prints out:

    SELECT s.name AS school, AVG(c.difficulty) AS avgDifficulty
    FROM school AS s
    INNER JOIN course AS c ON s.id = c.school_id
    GROUP BY school
    HAVING avgDifficulty > 1

### Sub-queries can be made by using `QueryFactory` to build sub-query

    String query = select()
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

## INSERT INTO statement

### Basic example

    String query = insertInto()
            .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
            .values()
                .value(101)
                .value("1980-04-12")
                .value("Miika")
                .value("Somero")
                .value(40)
            .build();

        logger.info(query);

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)

### Insert into select example

    String query = insertInto()
            .table("person")
            .sub(select()
                    .column("*")
                .from()
                    .table("student")
                .where(valueOf("age").lesserThan(18)))
        .build();

        logger.info(query);

Prints out:

    INSERT INTO person
    SELECT * FROM student
    WHERE age < 18

## UPDATE statement

### Basic example

    String query = update()
        .table("person")
            .column("age").value(50)
        .where(valueOf("id").equals(1)
            .or(valueOf("id").equals(2)))
        .build();

    logger.info(query);

Prints out:

    UPDATE person
    SET age = 50
    WHERE id = 1
    OR id = 2


## CREATE TABLE statement

    String query = create()
        .table("vehicles")
            .column("ID").type(DataType.INT).primaryKey()
            .column("name").type(DataType.VARCHAR_255).notNull()
            .column("model").type(DataType.VARCHAR_64)
            .column("manufacturer_id").type(DataType.INT)
        .foreignKey("manufacturer_id").references("ID", "manufacturer")
        .build();

    logger.info(query);

Prints out:

    CREATE TABLE vehicles (
        ID INT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        model VARCHAR(64),
        manufacturer_id INT,
        FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(ID)
    )

DELETE index and DELETE database are also supported, but examples are trivial.

## DELETE TABLE statement

### Basic example

    String query = deleteFrom()
        .table("address")
        .where(valueOf("city").equals("Helsinki)
            .or(valueOf("city").equals("Oulu")))
        .build();

    logger.info(query);

Prints out:

    DELETE FROM address
    WHERE city = 'Helsinki'
    OR city = 'Oulu'

## DROP statement

### Basic example

    String query = drop()
        .table("test_table")
        .build();

    logger.info(query);

Prints out:

    DROP TABLE test_table

## <a name="parametrized"></a>Parametrized queries

You should always use parametrized queries in untrusted environments, if your SQL query takes user input as parameters.

You can create parametrized `INSERT`, `UPDATE`, `WHERE` and `HAVING` statements to use with prepared statements.

### INSERT statement

    String query = insertInto()
        .table("person")
        .columns("id", "birthdate", "firstname", "lastname", "age")
        .values()
            .value("?)
            .value("?")
            .value("?")
            .value("?")
            .value("?")
        .build();

        logger.info(query);

Prints out:

    INSERT INTO person (id, birthdate, firstname, lastname, age)
    VALUES (?, ?, ?, ?, ?)

Now you can use this query with prepared statement:

    Connection conn = DriverManager.getConnection(path, user, password);  

    PreparedStatement stmt = conn.prepareStatement(query);  
    stmt.setInt(1, 1);  
    stmt.setString(2, "2021-05-20");  
    stmt.setString(3, "Miika");
    stmt.setString(4, "Somero");
    stmt.setInt(5, 40);

### UPDATE statement

    String query = update()
        .table("person")
        .column("firstname").value("?")
        .column("lastname").value("?")
        .column("age").value("?")
        .build();

        logger.info(query);

Prints out:

    UPDATE person
    SET firstname = ?, lastname = ?, age = ?

### WHERE clause in SELECT statement

    String query = select()
        .column("firstname")
        .from()
            .table("person");
        .where(valueOf("firstname").isIn("?", "?", "?"))
        .build();

        logger.info(query);

Prints out:

    SELECT firstname
    FROM person
    WHERE firstname IN (?, ?, ?)