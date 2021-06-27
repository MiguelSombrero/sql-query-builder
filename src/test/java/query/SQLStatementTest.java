package query;

import database.column.StringColumnValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SQLStatementTest {

    @Test
    public void testAppend() {
        SQLStatement statement = new SQLStatement(new StringBuilder());
        statement.append("miika");
        statement.append(" ");
        statement.append("Somero");
        assertEquals("miika Somero", statement.getQueryString());
    }

    @Test
    public void testAppendFront() {
        SQLStatement statement = new SQLStatement(new StringBuilder());
        statement.appendFront("somero");
        statement.appendFront("miika ");
        statement.appendFront("testaaja ");
        assertEquals("testaaja miika somero", statement.getQueryString());
    }

    @Test
    public void testGetQueryString() {
        SQLStatement statement = new SQLStatement(new StringBuilder("SELECT * FROM person WHERE age = ?"));
        assertEquals("SELECT * FROM person WHERE age = ?", statement.getQueryString());
    }

    @Test
    public void testMergeSubQuery() {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE ");
        StringBuilder subQueryString = new StringBuilder("firstname = ");

        Statement statement = new SQLStatement(queryString);
        Statement subStatement = new SQLStatement(subQueryString);

        StringColumnValue param = new StringColumnValue("Miika");
        subStatement.addParam(param);

        statement.mergeStatement(subStatement);

        assertEquals("SELECT * FROM person WHERE firstname = 'Miika'", statement.toString());
    }
}
