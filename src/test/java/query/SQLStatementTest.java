package query;

import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseTestBaseClass;

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
}
