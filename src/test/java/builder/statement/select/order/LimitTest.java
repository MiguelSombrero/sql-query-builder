package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class LimitTest extends DatabaseTestBaseClass {
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
    public void testLimitFromTable() throws SQLException {
        String query = table
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLimitFromTableWhere() throws SQLException {
        String query = table
                .where("age").greaterThan(18)
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLimitJoinTable() throws SQLException {
        String query = table
                .leftJoin("course").on("person.id = course.person_id")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN course ON person.id = course.person_id LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLimitGroupBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLimitGroupByHaving() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .having("age = 20")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age HAVING age = 20 LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLimitOrderBy() throws SQLException {
        String query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname LIMIT 100", query);
        assertThatQueryIsValidSQL(query);
    }

}
