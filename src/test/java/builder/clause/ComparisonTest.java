package builder.clause;

import builder.query.select.table.Table;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import builder.query.SQLQueryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.ConditionClauseBuilder.*;
import static org.junit.Assert.assertEquals;

public class ComparisonTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;
    private Table baseQuery;
    private Table allTypesBaseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = SQLQueryBuilder
                .select()
                    .column("firstname")
                .from()
                    .table("person");

        this.allTypesBaseQuery = this.SQLQueryBuilder
                .select()
                    .all()
                .from()
                    .table("all_types");
    }

    @Test
    public void testConditionStringEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").equals("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testConditionIntegerEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").equals(39))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 39", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").equals(120.1))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age = 120.1", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 13);
    }

    @Test
    public void testConditionAnyEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .equalsAny(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testConditionAllEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .equalsAll(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").greaterThan("1985-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate > '1985-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThan(30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDoubleGreaterThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").greaterThan(25.8))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age > 25.8", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAnyGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age")
                        .greaterThanAny(SQLQueryBuilder
                                .select()
                                .column("age")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(18))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > ANY (SELECT age FROM student WHERE age > 18)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionAllGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age")
                        .greaterThanAll(SQLQueryBuilder
                                .select()
                                .column("age")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > ALL (SELECT age FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").greaterThanOrEqual("1980-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate >= '1980-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanOrEqual(32))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 32", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDoubleGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").greaterThanOrEqual(27.3))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age >= 27.3", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAnyGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age")
                        .greaterThanOrEqualAny(SQLQueryBuilder
                                .select()
                                .column("age")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(30))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= ANY (SELECT age FROM student WHERE age > 30)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAllGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .greaterThanOrEqualAll(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").lesserThan("1985-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate < '1985-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThan(31))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 31", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleLesserThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").lesserThan(25.7))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age < 25.7", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAnyLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanAny(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
    }

    @Test
    public void testConditionAllLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanAll(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").lesserThanOrEqual("1980-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate <= '1980-02-28 21:00:00'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanOrEqual(30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 30", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").lesserThanOrEqual(25.6))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age <= 25.6", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAnyLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanOrEqualAny(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
    }

    @Test
    public void testConditionAllLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanOrEqualAll(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
    }

    @Test
    public void testConditionIsNull() throws SQLException {
        SelectQuery query = this.SQLQueryBuilder
                .select()
                    .all()
                .from()
                    .table("school")
                .where(valueOf("name").isNull())
                .build();

        assertEquals("SELECT * FROM school WHERE name IS NULL", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIsNotNull() throws SQLException {
        SelectQuery query = this.SQLQueryBuilder
                .select()
                    .all()
                .from()
                    .table("school")
                .where(valueOf("name").isNotNull())
                .build();

        assertEquals("SELECT * FROM school WHERE name IS NOT NULL", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionStringBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").isBetween("Ma", "Mi"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname BETWEEN 'Ma' AND 'Mi'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isBetween(35, 65))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 35 AND 65", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDoubleBetween() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").isBetween(18.5, 30.5))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age BETWEEN 18.5 AND 30.5", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionStartsWith() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").startsWith("Mii"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE 'Mii%'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionEndsWith() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").endsWith("ka"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%ka'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionContains() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").contains("iik"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%iik%'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfStrings() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname").isIn("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isIn(18, 19, 20, 21, 30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21, 30)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfDoubles() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").isIn(18.1, 19.2, 20.3, 25.6))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age IN (18.1, 19.2, 20.3, 25.6)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInSubQuery() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname").isInSub(SQLQueryBuilder
                    .select()
                        .column("lastname")
                    .from().table("student")
                    .where(valueOf("age").lesserThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN (SELECT lastname FROM student WHERE age < 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionExists() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(exists(SQLQueryBuilder
                        .select()
                            .all()
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
    }

    @Test
    public void testConditionNotExists() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(notExists(SQLQueryBuilder
                        .select()
                            .all()
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }
}
