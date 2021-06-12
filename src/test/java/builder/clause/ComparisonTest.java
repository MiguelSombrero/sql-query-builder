package builder.clause;

import builder.statement.select.table.Table;
import database.row.Row;
import query.dql.SelectQuery;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static builder.clause.WhereClauseFactory.*;
import static org.junit.Assert.assertEquals;

public class ComparisonTest extends DatabaseTestBaseClass {
    private QueryFactory queryFactory;
    private Table baseQuery;
    private Table allTypesBaseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());

        this.baseQuery = queryFactory
                .select()
                    .column("firstname")
                .from()
                    .table("person");

        this.allTypesBaseQuery = this.queryFactory
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
                        .equalsAny(queryFactory
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
                        .equalsAll(queryFactory
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
                        .greaterThanAny(queryFactory
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
                        .greaterThanAll(queryFactory
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
                        .greaterThanOrEqualAny(queryFactory
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
                        .greaterThanOrEqualAll(queryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").lesserThan("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate < '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThan(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18", query.toString());
    }

    @Test
    public void testConditionDoubleLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThan(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 18.5", query.toString());
    }

    @Test
    public void testConditionAnyLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanAny(queryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanAll(queryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("birthdate").lesserThanOrEqual("2020-02-28 21:00:00"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE birthdate <= '2020-02-28 21:00:00'", query.toString());
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanOrEqual(18))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18", query.toString());
    }

    @Test
    public void testConditionDoubleLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanOrEqual(18.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 18.5", query.toString());
    }

    @Test
    public void testConditionAnyLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanOrEqualAny(queryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ANY (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionAllLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .lesserThanOrEqualAll(queryFactory
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname <= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionIsNull() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isNull()
                        .and(valueOf("firstname").isNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NULL AND firstname IS NULL", query.toString());
    }

    @Test
    public void testConditionIsNotNull() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isNotNull()
                        .and(valueOf("firstname").isNotNull()))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IS NOT NULL AND firstname IS NOT NULL", query.toString());
    }

    @Test
    public void testConditionStringBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").isBetween("ika", "miiika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname BETWEEN 'ika' AND 'miiika'", query.toString());
    }

    @Test
    public void testConditionIntegerBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isBetween(18, 65)
                        .and(valueOf("firstname").isBetween("miika", "siika")))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18 AND 65 AND firstname BETWEEN 'miika' AND 'siika'", query.toString());
    }

    @Test
    public void testConditionDoubleBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isBetween(18.5, 65.5))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 18.5 AND 65.5", query.toString());
    }

    @Test
    public void testConditionStartsWith() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").startsWith("Mii"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE 'Mii%'", query.toString());
    }

    @Test
    public void testConditionEndsWith() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").endsWith("ka"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%ka'", query.toString());
    }

    @Test
    public void testConditionContains() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").contains("iik"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname LIKE '%iik%'", query.toString());
    }

    @Test
    public void testConditionInListOfStrings() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname").isIn("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query.toString());
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isIn(18, 19, 20, 21))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21)", query.toString());
    }

    @Test
    public void testConditionInListOfDoubles() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isIn(18.1, 19.2, 20.3, 21.4))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18.1, 19.2, 20.3, 21.4)", query.toString());
    }

    @Test
    public void testConditionInSubQuery() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname").isInSub(queryFactory
                    .select()
                        .all()
                    .from().table("student")
                    .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionExists() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(exists(queryFactory
                        .select()
                            .all()
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test
    public void testConditionNotExists() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(notExists(queryFactory
                        .select()
                            .all()
                        .from().table("student")
                        .where(valueOf("age").greaterThan(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfWithSQLInjection(){
        this.baseQuery
                .where(valueOf(";DROP").contains("miika"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionWithSQLInjection(){
        this.baseQuery
                .where(valueOf("firstname").contains(";DROP"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionWithParameterInColumn(){
        this.baseQuery
                .where(valueOf("?").contains("miika"))
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConditionWithParameterInValue(){
        this.baseQuery
                .where(valueOf("firstname").contains("?"))
                .build();
    }
}
