# Supported operations and data types

## <a name="validation"></a>User input validation

Sql-query-builder validates all user input that is comes in String format.
Double, int, long and other primitive types is not validated.

Implementation of the validators can be found in package `.../main/java/validation/`. 

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
  
## Supported data types

