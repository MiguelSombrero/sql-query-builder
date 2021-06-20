package builder.query.update;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import builder.query.SQLQueryBuilder;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import static org.junit.Assert.assertEquals;

public class UpdateValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;
    private FirstColumn baseQuery;

    @Before
    public void setUp() {
        initializeDatabase();
        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = SQLQueryBuilder
                .update()
                .table("person");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBadStringValue() {
        this.baseQuery
                .column("firstname").setString("Miika--Lassi Kari")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBadDateValue() {
        this.baseQuery
                .column("birthdate").setDate("1985-01-02 21:11:11")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDateTimeValue() {
        this.baseQuery
                .column("birthdate").setDateTime("1985-01-02")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTableWithSQLInjection() {
        SQLQueryBuilder
                .update()
                .table(";DROP")
                .column("firstname").setString("Miika")
                .column("lastname").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFirstColumnWithSQLInjection() {
        SQLQueryBuilder
                .update()
                .table("person")
                .column("--DROP").setString("Miika")
                .column("lastname").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateColumnWithSQLInjection() {
        SQLQueryBuilder
                .update()
                .table("person")
                .column("firstname").setString("Miika")
                .column(";DROP").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithSQLInjection() {
        SQLQueryBuilder
                .update()
                .table("person")
                    .column("firstname").setString("Miika")
                    .column("lastname").setString(";DROP")
                    .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithParameterInColumn() {
        SQLQueryBuilder
                .update()
                .table("person")
                .column("firstname").setString("Miika")
                .column("?").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithParameterInValue() {
        SQLQueryBuilder
                .update()
                .table("person")
                .column("firstname").setString("?")
                .build();
    }
}
