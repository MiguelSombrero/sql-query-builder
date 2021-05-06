package builder.statement.insert;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class InsertTest extends DatabaseTestBaseClass {

    @Test
    public void testInsertOneValue() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .columns("id")
                .values()
                    .value(100)
                .build();

        assertEquals("INSERT INTO person (id) VALUES (100)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertMultipleValues() throws SQLException {
        String query = QueryFactory
                .insertInto()
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

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertQuery() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
                .sub(QueryFactory
                        .select()
                            .column("id")
                            .column("birthdate")
                            .column("firstname")
                            .column("lastname")
                            .column("age")
                        .from()
                            .table("student")
                        .where(valueOf("age").greaterThan(18))
                )
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) SELECT id, birthdate, firstname, lastname, age FROM student WHERE age > 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertMultipleValuesWithoutSpecifyingColumns() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .values()
                    .value(102)
                    .value("1980-04-12")
                    .value("Miika")
                    .value("Somero")
                    .value(40)
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person VALUES (102, '1980-04-12', 'Miika', 'Somero', 40)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertQueryWithoutSpecifyingColumns() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .sub(QueryFactory
                        .select()
                            .column("*")
                        .from()
                            .table("student")
                        .where(valueOf("age").lesserThan(18))
                )
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person SELECT * FROM student WHERE age < 18", query);
        assertThatQueryIsValidSQL(query);
    }
}
