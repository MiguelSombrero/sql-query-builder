package builder.condition;

import builder.statement.select.table.Table;
import database.DatabaseTestBaseClass;
import factory.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;

import static factory.WhereClauseFactory.*;
import static junit.framework.Assert.assertEquals;

public class ComparisonTest extends DatabaseTestBaseClass {
    private Table table;

    @Before
    public void setUpQuery() throws ValidationException {
        initializeDatabase();

        this.table = QueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testConditionStringEquals() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("firstname").equals("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = 'Miika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerEquals() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").equals(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleEquals() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").equals(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 18.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAnyEquals() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .equalsAny(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ANY (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAllEquals() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .equalsAll(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ALL (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringGreaterThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("birthdate").greaterThan("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate > '2020-02-28 21:00:00'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerGreaterThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").greaterThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleGreaterThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").greaterThan(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAnyGreaterThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .greaterThanAny(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname > ANY (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAllGreaterThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .greaterThanAll(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname > ALL (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("birthdate").greaterThanOrEqual("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate >= '2020-02-28 21:00:00'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").greaterThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleGreaterThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").greaterThanOrEqual(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 18.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAnyGreaterThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .greaterThanOrEqualAny(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ANY (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAllGreaterThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .greaterThanOrEqualAll(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ALL (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("birthdate").lesserThan("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate < '2020-02-28 21:00:00'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").lesserThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleLesserThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").lesserThan(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAnyLesserThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .lesserThanAny(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ANY (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAllLesserThan() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .lesserThanAll(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ALL (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("birthdate").lesserThanOrEqual("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate <= '2020-02-28 21:00:00'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").lesserThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleLesserThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").lesserThanOrEqual(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAnyLesserThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .lesserThanOrEqualAny(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ANY (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionAllLesserThanOrEqual() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname")
                        .lesserThanOrEqualAll(QueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ALL (SELECT lastname FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIsNull() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isNull()
                        .and(valueOf("firstname").isNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NULL AND firstname IS NULL", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIsNotNull() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isNotNull()
                        .and(valueOf("firstname").isNotNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NOT NULL AND firstname IS NOT NULL", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStringBetween() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("firstname").isBetween("ika", "miiika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname BETWEEN 'ika' AND 'miiika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionIntegerBetween() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isBetween(18, 65)
                        .and(valueOf("firstname").isBetween("miika", "siika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18 AND 65 AND firstname BETWEEN 'miika' AND 'siika'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionDoubleBetween() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isBetween(18.5, 65.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18.5 AND 65.5", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionStartsWith() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("firstname").startsWith("Mii"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE 'Mii%'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionEndsWith() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("firstname").endsWith("ka"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%ka'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionContains() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("firstname").contains("iik"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%iik%'", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInListOfStrings() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("lastname").isIn("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isIn(18, 19, 20, 21))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInListOfDoubles() throws SQLException, ValidationException {
        String query = this.table
                .where(valueOf("age").isIn(18.1, 19.2, 20.3, 21.4))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18.1, 19.2, 20.3, 21.4)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionInSubQuery() throws SQLException, ValidationException {
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

    @Test
    public void testConditionExists() throws SQLException, ValidationException {
        String query = this.table
                .where(exists(QueryFactory
                        .select()
                        .column("*")
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE EXISTS (SELECT * FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test
    public void testConditionNotExists() throws SQLException, ValidationException {
        String query = this.table
                .where(notExists(QueryFactory
                        .select()
                        .column("*")
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT EXISTS (SELECT * FROM student WHERE age > 20)", query);
        assertThatQueryIsValidSQL(query);
    }

    @Test(expected = ValidationException.class)
    public void testValueOfWithSQLInjection() throws ValidationException {
        this.table
                .where(valueOf(";DROP").contains("miika"))
                .build();
    }

    @Test(expected = ValidationException.class)
    public void testConditionWithSQLInjection() throws ValidationException {
        this.table
                .where(valueOf("firstname").contains(";DROP"))
                .build();
    }
}