package builder.statement.insert;

import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Test;

import java.sql.SQLException;

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
                            .column("age")
                        .from()
                            .table("person")
                        .where("age").greaterThan(18)
                        .build()
                )
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) SELECT id, age FROM person WHERE age > 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertMultipleValuesWithoutSpecifyingColumns() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .values()
                    .value(101)
                    .value("1980-04-12")
                    .value("Miika")
                    .value("Somero")
                    .value(40)
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person VALUES (101, '1980-04-12', 'Miika', 'Somero', 40)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInsertQueryWithoutSpecifyingColumns() throws SQLException {
        String query = QueryFactory
                .insertInto()
                .table("person")
                .sub(QueryFactory
                        .select()
                            .column("id")
                            .column("age")
                        .from()
                            .table("person")
                        .where("age").greaterThan(18)
                        .build()
                )
                .build();

        logger.info(query);

        assertEquals("INSERT INTO person SELECT id, age FROM person WHERE age > 18", query);
        assertThatQueryIsValidSQL(query);
    }
}
