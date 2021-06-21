package builder.query.insert;

import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import query.InsertQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class InsertValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;

    @Before
    public void setUp() throws SQLException {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadStringValue() {
        InsertQuery query = SQLQueryBuilder
                .insert()
                .table("person")
                    .columns("firstname")
                .values()
                    .setString("not--supported")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadDateValue() {
        InsertQuery query = SQLQueryBuilder
                .insert()
                .table("person")
                .columns("birthdate")
                .values()
                .setDate("1978-04-03 21:00:00")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertBadTimestampValue() {
        InsertQuery query = SQLQueryBuilder
                .insert()
                .table("person")
                .columns("birthdate")
                .values()
                .setTimestamp("1978-04-02")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertWithSQLInjection() {
        SQLQueryBuilder
                .insert()
                .table(";DROP")
                    .columns("id")
                .values()
                    .setInt(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertColumnsWithSQLInjection() {
        SQLQueryBuilder
                .insert()
                .table("person")
                    .columns("id", ";DROP")
                .values()
                    .setInt(100)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithSQLInjection() {
        SQLQueryBuilder
                .insert()
                .table("person")
                    .columns("firstname")
                .values()
                    .setString(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInColumn() {
        SQLQueryBuilder
                .insert()
                .table("person")
                    .columns("?")
                .values()
                    .setString("Miika")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertValuesWithParameterInValue() {
        SQLQueryBuilder
                .insert()
                .table("person")
                .columns("lastname")
                .values()
                .setString("?")
                .build();
    }
}
