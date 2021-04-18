# sql-query-builder

![GitHub Actions](https://github.com/MiguelSombrero/sql-query-builder/workflows/Java%20CI%20with%20Maven/badge.svg)

**This app is under development**

Sql query builder is Java-library to build SQL query strings more easily.

## Examples

### SELECT

    String query = QueryFactory
        .select()
        .field("firstname").alias("first")
        .field("lastname").alias("last")
        .field("age")
        .from("person")
        .where("age").greaterThan(18)
        .build();

    logger.info(query)
    
Above code prints out:

    "SELECT firstname AS first, lastname AS last, age FROM person WHERE age > 18;"
    
## How to use

## Not yet implemented

In order to be implemented:
- Null and like operators (IS NULL, IS NOT NULL, LIKE, NOT LIKE)
- Sorting results (ORDER BY, DESC, ASC, ...)
- Limiting results (LIMIT)
- Insert clauses (INSERT INTO, VALUES, )
- Update clauses (UPDATE, SET, ...)
- Delete clauses (DELETE, ...)
- Grouping results (GROUP BY)
- Between operator (BETWEEN x AND y)
- Aggregate functions (MAX, MIN, AVG, SUM, COUNT, ...)
- Filtering aggregate results (HAVING)
- Exists operator (EXISTS)
- In operator (IN (...), IN (SELECT), ...)
- Any and All operators (ANY, ALL)
- Select into statement (SELECT INTO)
- Case statements (CASE - WHEN, THEN)
- Embedded SELECT and conditional clauses

And propably many more special cases ...

## Known issues

- Cannot alias join table. Need to change table design
