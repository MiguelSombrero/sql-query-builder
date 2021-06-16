# Supported operations

## SELECT

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

## INSERT

- INSERT INTO
    - table (optional columns)
- VALUES
    - (optional values)
    - sub-query

## UPDATE

- UPDATE
    - table
- SET
    - columns = values
- WHERE
    - condition
    
## CREATE

- CREATE
    - table, testutils, index
- INT, DOUBLE, TIMESTAMP, CHAR, VARCHAR
- NOT NULL, PRIMARY KEY, UNIQUE, AUTO_INCREMENT
- FOREIGN KEY key REFERENCES table(key)
- ON DELETE, ON UPDATE
  - cascade, restrict, set null, set default

## DELETE

- DELETE FROM
    - table
- WHERE
    - condition
    
## DROP

- DROP
    - table, testutils