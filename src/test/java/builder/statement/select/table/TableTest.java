package builder.statement.select.table;

import builder.statement.select.column.Column;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static factory.WhereClauseFactory.valueOf;
import static junit.framework.Assert.assertEquals;

public class TableTest extends DatabaseTestBaseClass {
    private Column column;

    @Before
    public void setUpQuery() throws ValidationException {
        initializeDatabase();

        this.column = QueryFactory
                .select()
                .column("*");
    }

    @Test
    public void testFromOneTable() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .build();

        assertEquals("SELECT * FROM person", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromMultipleTables() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                    .table("address")
                    .table("course")
                .build();

        assertEquals("SELECT * FROM person, address, course", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromSubQuery() throws SQLException, ValidationException {
        String query = QueryFactory
                .select()
                    .column("*")
                .from()
                    .sub(QueryFactory
                            .select()
                                .column("*")
                            .from()
                                .table("person")
                            .where(valueOf("age").greaterThan(20))
                    )
                .alias("p")
                .build();

        assertEquals("SELECT * FROM (SELECT * FROM person WHERE age > 20) AS p", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromMultipleTablesWithAliases() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person").alias("p")
                    .table("address").alias("a")
                    .table("course").alias("h")
                .build();

        assertEquals("SELECT * FROM person AS p, address AS a, course AS h", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromOneJoinTableWithAlias() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .leftJoin("address").alias("a").on("person.id", "a.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address AS a ON person.id = a.person_id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testInnerJoin() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .innerJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person INNER JOIN address ON person.id = address.person_id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testLeftJoin() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void rightJoin() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .rightJoin("address").on("person.id", "address.person_id")
                .build();

        assertEquals("SELECT * FROM person RIGHT JOIN address ON person.id = address.person_id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testMultipleJoins() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person")
                .leftJoin("address").on("person.id", "address.person_id")
                .innerJoin("course").on("person.id", "course.person_id")
                .rightJoin("school").on("course.school_id", "school.id")
                .build();

        assertEquals("SELECT * FROM person LEFT JOIN address ON person.id = address.person_id INNER JOIN course ON person.id = course.person_id RIGHT JOIN school ON course.school_id = school.id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testFromMultipleTablesAndJoinWithAliases() throws SQLException, ValidationException {
        String query = this.column
                .from()
                    .table("person").alias("p")
                .leftJoin("address").alias("a").on("p.id", "a.person_id")
                .innerJoin("course").alias("c").on("p.id", "c.person_id")
                .rightJoin("school").alias("s").on("c.school_id", "s.id")
                .build();

        assertEquals("SELECT * FROM person AS p LEFT JOIN address AS a ON p.id = a.person_id INNER JOIN course AS c ON p.id = c.person_id RIGHT JOIN school AS s ON c.school_id = s.id", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = ValidationException.class)
    public void testSelectTableWithSQLInjection() throws ValidationException {
        this.column
                .from()
                .table(";DROP")
                .leftJoin("address").alias("a").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testSelectJoinTableWithSQLInjection() throws ValidationException {
        this.column
                .from()
                .table("person")
                .leftJoin(";DROP").alias("a").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testSelectJoinTableAliasWithSQLInjection() throws ValidationException {
        this.column
                .from()
                .table("person")
                .leftJoin("address").alias(";DROP").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testSelectJoinTableOnWithSQLInjection() throws ValidationException {
        this.column
                .from()
                .table("person")
                .leftJoin("address").alias("a").on(";DROP", "a.person_id")
                .build();
    }
}
