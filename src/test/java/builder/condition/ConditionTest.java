package builder.condition;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static junit.framework.Assert.assertEquals;

public class ConditionTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        this.table = QueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testWhereAndCondition() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThan(18)
                        .and(valueOf("birthdate").greaterThan("2020-02-28T21:00:00.000")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereOrCondition() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThan(18)
                        .or(valueOf("birthdate").greaterThan("2020-02-28T21:00:00.000")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 OR birthdate > '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereAndOrConditions() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThan(18)
                        .and(valueOf("birthdate").greaterThan("2020-02-28T21:00:00.000"))
                        .or(valueOf("birthdate").lesserThan("2018-02-28T21:00:00.000")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18 AND birthdate > '2020-02-28T21:00:00.000' OR birthdate < '2018-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testWhereOrSubCondition() throws SQLException {
        String query = this.table
                .where(valueOf("age").lesserThan(18)
                        .orSub(valueOf("age").greaterThan(50)
                                .and(valueOf("age").lesserThan(60))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18 OR (age > 50 AND age < 60)", query);
        assertThatQueryIsValidSQL(query);
    }
}
