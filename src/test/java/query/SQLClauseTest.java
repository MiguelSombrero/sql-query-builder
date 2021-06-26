package query;

import database.column.ColumnValue;
import database.column.StringColumnValue;
import org.junit.Before;
import org.junit.Test;
import database.row.Row;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SQLClauseTest {

    @Test
    public void testMergeSubQuery() {
        StringBuilder queryString = new StringBuilder("SELECT * FROM person WHERE ");
        StringBuilder subQueryString = new StringBuilder("firstname = ");

        Clause clause = new SQLClause(queryString);
        Clause subClause = new SQLClause(subQueryString);

        StringColumnValue param = new StringColumnValue("Miika");
        subClause.addParam(param);

        clause.mergeClause(subClause);

        assertEquals("SELECT * FROM person WHERE firstname = 'Miika'", clause.toString());
    }
}
