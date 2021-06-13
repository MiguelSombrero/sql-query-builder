package builder.statement.select.table;

import builder.statement.select.column.ToFrom;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class TableTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private ToFrom baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .select()
                    .all();
    }

    @Test
    public void testFromOneTable() throws SQLException {
        SelectQuery query = this.baseQuery
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 5);
    }

    @Test
    public void testFromMultipleTables() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .column("firstname")
                    .column("street")
                    .column("difficulty")
                .from()
                    .table("person")
                    .table("address")
                    .table("course")
                .build();

        assertEquals("SELECT firstname, street, difficulty FROM person, address, course", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 3);
    }

    @Test
    public void testFromSubQuery() throws SQLException {
        SelectQuery query = this.baseQuery
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
        assertColumnCount(result, 5);
    }

    @Test
    public void testFromMultipleTablesWithAliases() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .column("p.id")
                    .column("a.city")
                    .column("h.name")
                .from()
                    .table("person").alias("p")
                    .table("address").alias("a")
                    .table("course").alias("h")
                .build();

        assertEquals("SELECT p.id, a.city, h.name FROM person AS p, address AS a, course AS h", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 3);
    }

    @Test
    public void testFromOneJoinTableWithAlias() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .column("person.id")
                .from()
                    .table("person")
                .leftJoin("address").alias("a").on("person.id", "a.person_id")
                .build();

        assertEquals("SELECT person.id FROM person LEFT JOIN address AS a ON person.id = a.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 1);
    }

    @Test
    public void testInnerJoin() throws SQLException {
        SelectQuery query = queryFactory
                .select()
                    .count("person.id")
                .from()
                    .table("person")
                .innerJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT COUNT(person.id) FROM person INNER JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testLeftJoin() throws SQLException {
        SelectQuery query = this.queryFactory
                .select()
                    .column("person.id")
                    .column("address.city")
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT person.id, address.city FROM person LEFT JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
        assertColumnCount(result, 2);
    }

    @Test
    public void testRightJoin() throws SQLException {
        SelectQuery query = this.queryFactory
                .select()
                    .column("person.id")
                    .column("address.city")
                .from()
                    .table("person")
                .rightJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT person.id, address.city FROM person RIGHT JOIN address ON person.id = address.person_id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 2);
    }

    @Test
    public void testMultipleJoins() throws SQLException {
        SelectQuery query = this.queryFactory
                .select()
                    .column("person.id")
                    .column("address.city")
                    .column("course.name")
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .innerJoin("course").on("person.id", "course.person_id")
                .rightJoin("school").on("course.school_id", "school.id")
                .build();

        assertEquals("SELECT person.id, address.city, course.name FROM person LEFT JOIN address ON person.id = address.person_id INNER JOIN course ON person.id = course.person_id RIGHT JOIN school ON course.school_id = school.id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 3);
    }

    @Test
    public void testFromMultipleTablesAndJoinWithAliases() throws SQLException {
        SelectQuery query = this.queryFactory
                .select()
                    .column("p.id")
                    .column("a.city")
                    .column("c.name")
                .from()
                    .table("person").alias("p")
                .leftJoin("address").alias("a").on("p.id", "a.person_id")
                .innerJoin("course").alias("c").on("p.id", "c.person_id")
                .rightJoin("school").alias("s").on("c.school_id", "s.id")
                .build();

        assertEquals("SELECT p.id, a.city, c.name FROM person AS p LEFT JOIN address AS a ON p.id = a.person_id INNER JOIN course AS c ON p.id = c.person_id RIGHT JOIN school AS s ON c.school_id = s.id", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
        assertColumnCount(result, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableWithSQLInjection() {
        this.baseQuery
                .from()
                .table(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableAliasWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person").alias(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectSubQueryAliasWithSQLInjection() {
        this.baseQuery
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
        this.baseQuery
                .from()
                .table("person")
                .leftJoin(";DROP").alias("a").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableAliasWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias(";DROP").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias("a").on(";DROP", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnJoinWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias("a").on("person.id", ";DROP")
                .build();
    }
}
