package builder.condition;

import builder.statement.select.table.Table;
import database.DatabaseConnection;
import database.DatabaseTestBaseClass;
import factory.SelectQueryFactory;
import org.junit.Before;
import org.junit.Test;
import query.Query;

import java.sql.SQLException;

import static factory.WhereClauseFactory.*;
import static org.junit.Assert.assertEquals;

public class ComparisonTest extends DatabaseTestBaseClass {
    private SelectQueryFactory selectQueryFactory;
    private Table table;

    @Before
    public void setUpQuery() throws SQLException {
        initializeDatabase();

        selectQueryFactory = new SelectQueryFactory(DatabaseConnection.getDataSource());

        this.table = selectQueryFactory
                .select()
                .column("firstname")
                .from()
                    .table("person");
    }

    @Test
    public void testConditionStringEquals() throws SQLException {
        Query query = this.table
                .where(valueOf("firstname").equals("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = 'Miika'", query.toString());
    }

    @Test
    public void testConditionIntegerEquals() throws SQLException {
        Query query = this.table
                .where(valueOf("age").equals(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 18", query.toString());
    }

    @Test
    public void testConditionDoubleEquals() throws SQLException {
        Query query = this.table
                .where(valueOf("age").equals(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 18.5", query.toString());
    }

    @Test
    public void testConditionAnyEquals() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .equalsAny(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllEquals() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .equalsAll(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringGreaterThan() throws SQLException {
        Query query = this.table
                .where(valueOf("birthdate").greaterThan("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate > '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerGreaterThan() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18", query.toString());
    }

    @Test
    public void testConditionDoubleGreaterThan() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThan(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 18.5", query.toString());
    }

    @Test
    public void testConditionAnyGreaterThan() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .greaterThanAny(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname > ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllGreaterThan() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .greaterThanAll(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname > ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("birthdate").greaterThanOrEqual("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate >= '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 18", query.toString());
    }

    @Test
    public void testConditionDoubleGreaterThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("age").greaterThanOrEqual(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 18.5", query.toString());
    }

    @Test
    public void testConditionAnyGreaterThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .greaterThanOrEqualAny(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllGreaterThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .greaterThanOrEqualAll(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException {
        Query query = this.table
                .where(valueOf("birthdate").lesserThan("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate < '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException {
        Query query = this.table
                .where(valueOf("age").lesserThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18", query.toString());
    }

    @Test
    public void testConditionDoubleLesserThan() throws SQLException {
        Query query = this.table
                .where(valueOf("age").lesserThan(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18.5", query.toString());
    }

    @Test
    public void testConditionAnyLesserThan() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .lesserThanAny(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllLesserThan() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .lesserThanAll(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("birthdate").lesserThanOrEqual("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate <= '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("age").lesserThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18", query.toString());
    }

    @Test
    public void testConditionDoubleLesserThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("age").lesserThanOrEqual(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18.5", query.toString());
    }

    @Test
    public void testConditionAnyLesserThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .lesserThanOrEqualAny(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllLesserThanOrEqual() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname")
                        .lesserThanOrEqualAll(selectQueryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionIsNull() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isNull()
                        .and(valueOf("firstname").isNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NULL AND firstname IS NULL", query.toString());
    }

    @Test
    public void testConditionIsNotNull() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isNotNull()
                        .and(valueOf("firstname").isNotNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NOT NULL AND firstname IS NOT NULL", query.toString());
    }

    @Test
    public void testConditionStringBetween() throws SQLException {
        Query query = this.table
                .where(valueOf("firstname").isBetween("ika", "miiika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname BETWEEN 'ika' AND 'miiika'", query.toString());
    }

    @Test
    public void testConditionIntegerBetween() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isBetween(18, 65)
                        .and(valueOf("firstname").isBetween("miika", "siika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18 AND 65 AND firstname BETWEEN 'miika' AND 'siika'", query.toString());
    }

    @Test
    public void testConditionDoubleBetween() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isBetween(18.5, 65.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18.5 AND 65.5", query.toString());
    }

    @Test
    public void testConditionStartsWith() throws SQLException {
        Query query = this.table
                .where(valueOf("firstname").startsWith("Mii"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE 'Mii%'", query.toString());
    }

    @Test
    public void testConditionEndsWith() throws SQLException {
        Query query = this.table
                .where(valueOf("firstname").endsWith("ka"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%ka'", query.toString());
    }

    @Test
    public void testConditionContains() throws SQLException {
        Query query = this.table
                .where(valueOf("firstname").contains("iik"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%iik%'", query.toString());
    }

    @Test
    public void testConditionInListOfStrings() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname").isIn("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query.toString());
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isIn(18, 19, 20, 21))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21)", query.toString());
    }

    @Test
    public void testConditionInListOfDoubles() throws SQLException {
        Query query = this.table
                .where(valueOf("age").isIn(18.1, 19.2, 20.3, 21.4))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18.1, 19.2, 20.3, 21.4)", query.toString());
    }

    @Test
    public void testConditionInSubQuery() throws SQLException {
        Query query = this.table
                .where(valueOf("lastname").isInSub(selectQueryFactory
                    .select()
                        .column("*")
                    .from().table("student")
                    .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionExists() throws SQLException {
        Query query = this.table
                .where(exists(selectQueryFactory
                        .select()
                        .column("*")
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionNotExists() throws SQLException {
        Query query = this.table
                .where(notExists(selectQueryFactory
                        .select()
                        .column("*")
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testMultipleConditionWithParameters() {
        Query query = this.table
                .where(valueOf("firstname").equals("?")
                        .and(valueOf("age").lesserThan("?")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = ? AND age < ?", query.toString());
    }

    @Test
    public void testConditionInWithParameters() {
        Query query = this.table
                .where(valueOf("firstname").isIn("?", "?", "?"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname IN (?, ?, ?)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfWithSQLInjection(){
        this.table
                .where(valueOf(";DROP").contains("miika"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionWithSQLInjection(){
        this.table
                .where(valueOf("firstname").contains(";DROP"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionWithParameterInColumn(){
        this.table
                .where(valueOf("?").contains("miika"))
                .build();
    }
}
