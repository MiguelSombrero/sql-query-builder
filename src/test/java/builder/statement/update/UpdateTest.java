package builder.statement.update;

import query.QueryFactory;
import query.dml.DMLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static builder.condition.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class UpdateTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;

    @Before
    public void setUp() {
        initializeDatabase();
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testUpdateIntegerValue() throws SQLException {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                    .column("age").value(50)
                .build();

        assertEquals("UPDATE person SET age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateDoubleValue() throws SQLException {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                .column("age").value(50.5)
                .build();

        assertEquals("UPDATE person SET age = 50.5", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateStringValue() throws SQLException {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika'", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateMultipleValues() throws SQLException {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                    .column("lastname").value("Somero")
                    .column("age").value(50)
                .build();

        assertEquals("UPDATE person SET firstname = 'Miika', lastname = 'Somero', age = 50", query.toString());

        int result = query.execute();

        assertEquals(3, result);
    }

    @Test
    public void testUpdateWithCondition() throws SQLException {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                    .column("age").value(50)
                .where(valueOf("id").equals(1)
                        .or(valueOf("id").equals(2)))
                .build();

        assertEquals("UPDATE person SET age = 50 WHERE id = 1 OR id = 2", query.toString());

        int result = query.execute();

        assertEquals(2, result);
    }

    @Test
    public void testUpdateMultipleValuesWithParameters() {
        DMLQuery query = queryFactory
                .update()
                .table("person")
                    .column("firstname").value("?")
                    .column("lastname").value("?")
                    .column("age").value("?")
                .build();

        assertEquals("UPDATE person SET firstname = ?, lastname = ?, age = ?", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTableWithSQLInjection() {
        queryFactory
                .update()
                .table(";DROP")
                .column("firstname").value("Miika")
                .column("lastname").value("Somero")
                .column("age").value(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateFirstColumnWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                .column("--DROP").value("Miika")
                .column("lastname").value("Somero")
                .column("age").value(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateColumnWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                .column("firstname").value("Miika")
                .column(";DROP").value("Somero")
                .column("age").value(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithSQLInjection() {
        queryFactory
                .update()
                .table("person")
                    .column("firstname").value("Miika")
                    .column("lastname").value(";DROP")
                    .column("age").value(50)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateValueWithParameterInColumn() {
        queryFactory
                .update()
                .table("person")
                .column("firstname").value("Miika")
                .column("?").value("Somero")
                .column("age").value(50)
                .build();
    }
}
