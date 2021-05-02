package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class OrderByTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                    .column("*")
                .from()
                    .table("person");
    }

    @Test
    public void testOrderBy() throws SQLException {
        String query = table
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person ORDER BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testOrderByWhere() throws SQLException {
        String query = table
                .where("age").greaterThan(18)
                .orderBy()
                .column("firstname")
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 ORDER BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testOrderByGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleOrderByGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                    .column("lastname")
                    .column("age")
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname, lastname, age;", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleOrderByGroupByWithAscDesc() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname").asc()
                    .column("lastname").desc()
                    .column("age").asc()
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname ASC, lastname DESC, age ASC;", query);
        assertThatQueryIsValidSQL(query);
    }
}
