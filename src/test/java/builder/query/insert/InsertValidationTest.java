package builder.query.insert;

import org.junit.Before;
import org.junit.Test;
import query.QueryFactory;
import query.dml.InsertQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class InsertValidationTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadIntegerValue() {
        InsertQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("id")
                .values()
                    .setInt(Integer.MAX_VALUE)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadDoubleValue() {
        InsertQuery query = queryFactory
                .insertInto()
                .table("all_types")
                    .columns("age")
                .values()
                    .setDouble(Double.MAX_VALUE)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadStringValue() {
        InsertQuery query = queryFactory
                .insertInto()
                .table("person")
                    .columns("firstname")
                .values()
                    .setString("not--supported")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadDateValue() {
        InsertQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("birthdate")
                .values()
                .setDate("1978-04-03 21:00:00")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadDateTimeValue() {
        InsertQuery query = queryFactory
                .insertInto()
                .table("person")
                .columns("birthdate")
                .values()
                .setDateTime("1978-04-02")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWithSQLInjection() {
        queryFactory
                .insertInto()
                .table(";DROP")
                    .columns("id")
                .values()
                    .setInt(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertColumnsWithSQLInjection() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("id", ";DROP")
                .values()
                    .setInt(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithSQLInjection() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("firstname")
                .values()
                    .setString(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInColumn() {
        queryFactory
                .insertInto()
                .table("person")
                    .columns("?")
                .values()
                    .setString("Miika")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInValue() {
        queryFactory
                .insertInto()
                .table("person")
                .columns("lastname")
                .values()
                .setString("?")
                .build();
    }
}
