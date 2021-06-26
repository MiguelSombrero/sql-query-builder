# Supported operations and data types

## <a name="validation"></a>User input validation

Sql-query-builder validates all user input that is comes in String format.
Double, int, long and other primitive types is not validated.

Implementation of the validators can be found in package `src/main/java/validation/`. 

Validators is used by `ValueAppender` class found in package `src/main/java/builder/appender`.
User input is validated before appending it to the query string.

## <a name="parametrized"></a>Parametrized queries

Sql-query-builder parametrizes all value type user input by default. Value type
mean statements like `WHERE age > value`, `INSERT INTO ... VALUES (values)` and `UPDATE ... column1 = value1, column2 = value2`.

Column and table names etc. is not parametrized so **if you use this library in untrusted environment, do not
take user input in any other fields than values**. For example in statement `SELECT * FROM person` table name `person` is not parametrized in query
and might be vulnerable to SQL injection, even if the input is validated.

## Supported SQL operations

### SELECT

- SELECT, SELECT DISTINCT, SELECT TOP
    - column, min(column), max(column), avg(column), sum(column)
- FROM
    - table, sub-query
- AS
    - column, table, join table, sub-query
- LEFT JOIN, RIGHT JOIN, INNER JOIN
- WHERE
    - condition
    - =, <, >, <=, =>
    - EXISTS, ALL, ANY, IS NULL, BETWEEN, LIKE
    - IN
        - sub-query, list of values
    - AND, OR, NOT
- GROUP BY column
    - HAVING condition
- ORDER BY column
    - ASC, DESC
- LIMIT
- OFFSET

### INSERT

- INSERT INTO
    - table (optional columns)
- VALUES
    - (optional values)
    - sub-query

### UPDATE

- UPDATE
    - table
- SET
    - columns = values
- WHERE
    - condition
    
### CREATE

- CREATE
    - table, database, index
- INT, DOUBLE, TIMESTAMP, CHAR, VARCHAR
- NOT NULL, PRIMARY KEY, UNIQUE, AUTO_INCREMENT
- FOREIGN KEY key REFERENCES table(key)
- ON DELETE, ON UPDATE
  - cascade, restrict, set null, set default

### DELETE

- DELETE FROM
    - table
- WHERE
    - condition
    
### DROP

- DROP
    - table, database
- IF EXISTS
  
## Supported data types

Sql-query-builder does not limit the data types when creating tables.
It's up to your database system is the `CREATE` statements supported or not.

However, sql-query-builder maps SQL data types to Java data types in `SELECT`, `INSERT` and `UPDATE` statements.

Supported mappings are:

- CHAR, NCHAR, LONGNVARCHAR, LONGVARCHAR, VARCHAR, NVARCHAR
  - Is mapped to String
- INTEGER
  - Is mapped to Integer
- SMALLINT
  - Is mapped to Short
- BIGINT
  - Is mapped to Long
- DOUBLE, FLOAT, REAL
  - Is mapped to Double
- BOOLEAN, TINYINT, BIT
  - Is mapped to Boolean
- DATE
  - Is mapped to java.sql.Date
- TIME
  - Is mapper to java.sql.Time
- TIMESTAMP
  - Is mapped to java.sql.TIMESTAMP
- BLOB, BINARY, VARBINARY, LONGVARBINARY
  - Is mapper to byte array
- DECIMAL, NUMERIC
  - Is mapped to BigDecimal
