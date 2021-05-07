package builder.statement.clause.where;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static factory.QueryFactory.valueOf;
import static junit.framework.Assert.assertEquals;

public class ComparisonTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() {
        this.table = QueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testConditionStringEquals() throws SQLException {
        String query = this.table
                .where(valueOf("firstname").equals("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = 'Miika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerEquals() throws SQLException {
        String query = this.table
                .where(valueOf("age").equals(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringGreaterThan() throws SQLException {
        String query = this.table
                .where(valueOf("birthdate").greaterThan("2020-02-28T21:00:00.000"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate > '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerGreaterThan() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() throws SQLException {
        String query = this.table
                .where(valueOf("birthdate").greaterThanOrEqual("2020-02-28T21:00:00.000"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate >= '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() throws SQLException {
        String query = this.table
                .where(valueOf("age").greaterThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException {
        String query = this.table
                .where(valueOf("birthdate").lesserThan("2020-02-28T21:00:00.000"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate < '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException {
        String query = this.table
                .where(valueOf("age").lesserThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException {
        String query = this.table
                .where(valueOf("birthdate").lesserThanOrEqual("2020-02-28T21:00:00.000"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate <= '2020-02-28T21:00:00.000'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException {
        String query = this.table
                .where(valueOf("age").lesserThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIsNull() throws SQLException {
        String query = this.table
                .where(valueOf("age").isNull().and("firstname").isNull())
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NULL AND firstname IS NULL", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIsNotNull() throws SQLException {
        String query = this.table
                .where(valueOf("age").isNotNull().and("firstname").isNotNull())
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NOT NULL AND firstname IS NOT NULL", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionBetween() throws SQLException {
        String query = this.table
                .where(valueOf("age").isBetween(18, 65).and("firstname").isBetween("miika", "siika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18 AND 65 AND firstname BETWEEN 'miika' AND 'siika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionLike() throws SQLException {
        String query = this.table
                .where(valueOf("firstname").isLike("%ika%"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%ika%'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException {
        String query = this.table
                .where(valueOf("age").isIn(18, 19, 20, 21))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInListOfStrings() throws SQLException {
        String query = this.table
                .where(valueOf("lastname").isIn("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInSubQuery() throws SQLException {
        String query = this.table
                .where(valueOf("lastname").isInSub(QueryFactory
                    .select()
                        .column("*")
                    .from().table("student")
                    .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN (SELECT * FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }
}
