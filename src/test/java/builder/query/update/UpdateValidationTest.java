package builder.query.update;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import query.QueryFactory;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import static org.junit.Assert.assertEquals;

public class UpdateValidationTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private FirstColumn baseQuery;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .update()
                .table("person");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBadIntegerValue() {
        this.baseQuery
                .column("age").setInt(Integer.MIN_VALUE)
                .build();
    }

    @Ignore("Validation does not work yet on decimal values")
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBadDoubleValue() {
        this.baseQuery
                .column("age").setDouble(Double.MIN_VALUE)
                .build();
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
        queryFactory
                .update()
                .table(";DROP")
                .column("firstname").setString("Miika")
                .column("lastname").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFirstColumnWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                .column("--DROP").setString("Miika")
                .column("lastname").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateColumnWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                .column("firstname").setString("Miika")
                .column(";DROP").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                    .column("firstname").setString("Miika")
                    .column("lastname").setString(";DROP")
                    .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithParameterInColumn() {
        queryFactory
                .update()
                .table("person")
                .column("firstname").setString("Miika")
                .column("?").setString("Somero")
                .column("age").setInt(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithParameterInValue() {
        queryFactory
                .update()
                .table("person")
                .column("firstname").setString("?")
                .build();
    }
}
