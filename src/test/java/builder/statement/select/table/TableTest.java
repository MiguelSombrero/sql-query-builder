package builder.statement.select.table;

import builder.statement.select.column.ToFrom;
import query.dql.Row;
import query.dql.DQLQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.condition.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class TableTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private ToFrom column;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.column = queryFactory
                .select()
                    .all();
    }

    @Test
    public void testFromOneTable() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testFromMultipleTables() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                    .table("address")
                    .table("course")
                .build();

        assertEquals("SELECT * FROM person, address, course", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 15);
    }

    @Test
    public void testFromSubQuery() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .sub(queryFactory
                            .select()
                                .all()
                            .from()
                                .table("person")
                            .where(valueOf("age").greaterThan(20))
                    )
                .alias("p")
                .build();

        assertEquals("SELECT * FROM (SELECT * FROM person WHERE age > 20) AS p", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 5);
    }

    @Test
    public void testFromMultipleTablesWithAliases() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person").alias("p")
                    .table("address").alias("a")
                    .table("course").alias("h")
                .build();

        assertEquals("SELECT * FROM person AS p, address AS a, course AS h", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 15);
    }

    @Test
    public void testFromOneJoinTableWithAlias() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .leftJoin("address").alias("a").on("person.id", "a.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address AS a ON person.id = a.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 10);
    }

    @Test
    public void testInnerJoin() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .innerJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person INNER JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 10);
    }

    @Test
    public void testLeftJoin() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertRowLength(result, 10);
    }

    @Test
    public void rightJoin() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .rightJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person RIGHT JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 10);
    }

    @Test
    public void testMultipleJoins() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .innerJoin("course").on("person.id", "course.person_id")
                .rightJoin("school").on("course.school_id", "school.id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id INNER JOIN course ON person.id = course.person_id RIGHT JOIN school ON course.school_id = school.id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 17);
    }

    @Test
    public void testFromMultipleTablesAndJoinWithAliases() throws SQLException {
        DQLQuery query = this.column
                .from()
                    .table("person").alias("p")
                .leftJoin("address").alias("a").on("p.id", "a.person_id")
                .innerJoin("course").alias("c").on("p.id", "c.person_id")
                .rightJoin("school").alias("s").on("c.school_id", "s.id")
                .build();

        assertEquals("SELECT * FROM person AS p LEFT JOIN address AS a ON p.id = a.person_id INNER JOIN course AS c ON p.id = c.person_id RIGHT JOIN school AS s ON c.school_id = s.id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertRowLength(result, 17);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableWithSQLInjection() {
        this.column
                .from()
                .table(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableAliasWithSQLInjection() {
        this.column
                .from()
                .table("person").alias(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectSubQueryAliasWithSQLInjection() throws SQLException {
        this.column
                .from()
                .sub(queryFactory
                        .select()
                            .column("*")
                        .from()
                            .table("person")
                )
                .alias(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableWithSQLInjection() {
        this.column
                .from()
                .table("person")
                .leftJoin(";DROP").alias("a").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableAliasWithSQLInjection() {
        this.column
                .from()
                .table("person")
                .leftJoin("address").alias(";DROP").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnWithSQLInjection() {
        this.column
                .from()
                .table("person")
                .leftJoin("address").alias("a").on(";DROP", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnJoinWithSQLInjection() {
        this.column
                .from()
                .table("person")
                .leftJoin("address").alias("a").on("person.id", ";DROP")
                .build();
    }
}
