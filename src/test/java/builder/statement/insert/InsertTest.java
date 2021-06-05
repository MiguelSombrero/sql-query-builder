package builder.statement.insert;

import query.dml.DMLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.clause.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class InsertTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testInsertOneIntegerValue() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("id")
                .values()
                    .value(100)
                .build();

        assertEquals("INSERT INTO person (id) VALUES (100)", query.toString());

        int result = query.execute();

        assertEquals(100, result);
    }

    @Test
    public void testInsertOneDoubleValue() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("age")
                .values()
                    .value(19.5)
                .build();

        assertEquals("INSERT INTO person (age) VALUES (19.5)", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertOneStringValue() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("firstname")
                .values()
                    .value("Miika-Lassi Kari")
                .build();

        assertEquals("INSERT INTO person (firstname) VALUES ('Miika-Lassi Kari')", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertMultipleValues() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                    .columns("id", "birthdate", "firstname", "lastname", "age")
                .values()
                    .value(101)
                    .value("1980-04-12")
                    .value("Miika")
                    .value("Somero")
                    .value(40.5)
                .build();

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) VALUES (101, '1980-04-12', 'Miika', 'Somero', 40.5)", query.toString());

        int result = query.execute();

        assertEquals(101, result);

    }

    @Test
    public void testInsertQuery() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("id", "birthdate", "firstname", "lastname", "age")
                .sub(queryFactory
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

        assertEquals("INSERT INTO person (id, birthdate, firstname, lastname, age) SELECT id, birthdate, firstname, lastname, age FROM student WHERE age > 18", query.toString());

        int result = query.execute();

        assertEquals(4, result);
    }

    @Test
    public void testInsertMultipleValuesWithoutSpecifyingColumns() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .values()
                    .value(102)
                    .value("1980-04-12")
                    .value("Miika")
                    .value("Somero")
                    .value(40)
                .build();

        assertEquals("INSERT INTO person VALUES (102, '1980-04-12', 'Miika', 'Somero', 40)", query.toString());

        int result = query.execute();

        assertEquals(102, result);
    }

    @Test
    public void testInsertQueryWithoutSpecifyingColumns() throws SQLException {
        DMLQuery query = queryFactory
                .insertInto()
                .table("person")
                .sub(queryFactory
                        .select()
                            .all()
                        .from()
                            .table("student")
                        .where(valueOf("age").lesserThan(18))
                )
                .build();

        assertEquals("INSERT INTO person SELECT * FROM student WHERE age < 18", query.toString());

        int result = query.execute();

        assertEquals(5, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWithSQLInjection() {
        queryFactory
                .insertInto()
                .table(";DROP")
                    .columns("id")
                .values()
                    .value(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertColumnsWithSQLInjection() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("id", ";DROP")
                .values()
                    .value(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithSQLInjection() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("firstname")
                .values()
                    .value(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInColumn() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("?")
                .values()
                    .value("Miika")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInValue() {
        queryFactory
                .insertInto()
                .table("person")
                .columns("lastname")
                .values()
                .value("?")
                .build();
    }
}
