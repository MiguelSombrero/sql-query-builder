package builder.statement.select.order;

import builder.statement.select.table.Table;
import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.HavingClauseFactory.count;
import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class LimitTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() throws SQLException {
        initializeDatabase();

        SelectQueryFactory selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getConnection());

        this.table = selectQueryFactory
                .select()
                    .column("*")
                .from()
                    .table("person");
    }

    @Test
    public void testLimitFromTable() throws SQLException {
        Query query = table
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person LIMIT 100", query.toString());
    }

    @Test
    public void testLimitFromTableWhere() throws SQLException {
        Query query = table
                .where(valueOf("age").greaterThan(18))
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person WHERE age > 18 LIMIT 100", query.toString());
    }

    @Test
    public void testLimitJoinTable() throws SQLException {
        Query query = table
                .leftJoin("course").on("person.id", "course.person_id")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN course ON person.id = course.person_id LIMIT 100", query.toString());
    }

    @Test
    public void testLimitGroupBy() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age LIMIT 100", query.toString());
    }

    @Test
    public void testLimitGroupByHaving() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .having(count("age").greaterThan(20))
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age HAVING COUNT(age) > 20 LIMIT 100", query.toString());
    }

    @Test
    public void testLimitOrderBy() throws SQLException {
        Query query = table
                .groupBy()
                    .column("age")
                .orderBy()
                    .column("firstname")
                .limit(100)
                .build();

        assertEquals("SELECT * FROM person GROUP BY age ORDER BY firstname LIMIT 100", query.toString());
    }

    @Test
    public void testLimitOffset() throws SQLException {
        Query query = table
                .limit(100)
                .offset(20)
                .build();

        assertEquals("SELECT * FROM person LIMIT 100 OFFSET 20", query.toString());
    }
}
