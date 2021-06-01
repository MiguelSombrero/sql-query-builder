package builder.statement.select.table;

import builder.statement.select.column.Column;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;

public class TableTest extends DatabaseTestBaseClass {
    private SelectQueryFactory selectQueryFactory;
    private Column column;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.column = selectQueryFactory
                .select()
                .column("*");
    }

    @Test
    public void testFromOneTable() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query.toString());
    }

    @Test
    public void testFromMultipleTables() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                    .table("address")
                    .table("course")
                .build();

        assertEquals("SELECT * FROM person, address, course", query.toString());
    }

    @Test
    public void testFromSubQuery() throws SQLException {
        Query query = this.column
                .from()
                    .sub(selectQueryFactory
                            .select()
                                .column("*")
                            .from()
                                .table("person")
                            .where(valueOf("age").greaterThan(20))
                    )
                .alias("p")
                .build();

        assertEquals("SELECT * FROM (SELECT * FROM person WHERE age > 20) AS p", query.toString());
    }

    @Test
    public void testFromMultipleTablesWithAliases() throws SQLException {
        Query query = this.column
                .from()
                    .table("person").alias("p")
                    .table("address").alias("a")
                    .table("course").alias("h")
                .build();

        assertEquals("SELECT * FROM person AS p, address AS a, course AS h", query.toString());
    }

    @Test
    public void testFromOneJoinTableWithAlias() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .leftJoin("address").alias("a").on("person.id", "a.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address AS a ON person.id = a.person_id", query.toString());
    }

    @Test
    public void testInnerJoin() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .innerJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person INNER JOIN address ON person.id = address.person_id", query.toString());
    }

    @Test
    public void testLeftJoin() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id", query.toString());
    }

    @Test
    public void rightJoin() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .rightJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person RIGHT JOIN address ON person.id = address.person_id", query.toString());
    }

    @Test
    public void testMultipleJoins() throws SQLException {
        Query query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .innerJoin("course").on("person.id", "course.person_id")
                .rightJoin("school").on("course.school_id", "school.id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id INNER JOIN course ON person.id = course.person_id RIGHT JOIN school ON course.school_id = school.id", query.toString());
    }

    @Test
    public void testFromMultipleTablesAndJoinWithAliases() throws SQLException {
        Query query = this.column
                .from()
                    .table("person").alias("p")
                .leftJoin("address").alias("a").on("p.id", "a.person_id")
                .innerJoin("course").alias("c").on("p.id", "c.person_id")
                .rightJoin("school").alias("s").on("c.school_id", "s.id")
                .build();

        assertEquals("SELECT * FROM person AS p LEFT JOIN address AS a ON p.id = a.person_id INNER JOIN course AS c ON p.id = c.person_id RIGHT JOIN school AS s ON c.school_id = s.id", query.toString());
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
                .sub(selectQueryFactory
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
