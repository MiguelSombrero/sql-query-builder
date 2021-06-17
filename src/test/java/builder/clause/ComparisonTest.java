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
                .where(valueOf("firstname").equalsString("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname = 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 1);
    }

    @Test
    public void testConditionDateEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").equalsDate("2018-01-02"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate = '2018-01-02'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").equalsDateTime("2018-01-02 21:12:01"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime = '2018-01-02T21:12:01'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").equalsInteger(39))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age = 39", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionLongEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").equalsLong(9223372036854775806L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash = 9223372036854775806", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").equalsDouble(120.1))
                .build();

        assertEquals("SELECT * FROM all_types WHERE age = 120.1", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
        assertColumnCount(result, 13);
    }

    @Test
    public void testConditionBooleanEquals() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("active").equalsBoolean(true))
                .build();

        assertEquals("SELECT * FROM all_types WHERE active = true", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionAnyEquals() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("lastname")
                        .equalsAny(SQLQueryBuilder
                                .select()
                                .column("lastname")
                                .from().table("student")
                                .where(valueOf("age").greaterThanInteger(20))))
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
                                .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname = ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").greaterThanString("Mii"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname > 'Mii'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateGreaterThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").greaterThanDate("2019-01-01"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate > '2019-01-01'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeGreaterThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").greaterThanDateTime("2019-02-28 21:00:01"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime > '2019-02-28T21:00:01'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerGreaterThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanInteger(30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > 30", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionLongGreaterThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").greaterThanLong(922337223234222806L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash > 922337223234222806", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleGreaterThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").greaterThanDouble(25.8))
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
                                .where(valueOf("age").greaterThanInteger(18))))
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
                                .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age > ALL (SELECT age FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").greaterThanOrEqualString("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname >= 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").greaterThanOrEqualDate("2019-02-28"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate >= '2019-02-28'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").greaterThanOrEqualDateTime("2019-02-28 21:00:03"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime >= '2019-02-28T21:00:03'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").greaterThanOrEqualInteger(32))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age >= 32", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionLongGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").greaterThanOrEqualLong(922337223234222806L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash >= 922337223234222806", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDoubleGreaterThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").greaterThanOrEqualDouble(27.3))
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
                                .where(valueOf("age").greaterThanInteger(30))))
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
                                .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname >= ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }

    @Test
    public void testConditionStringLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").lesserThanString("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname < 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDateLesserThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").lesserThanDate("2019-02-28"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate < '2019-02-28'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeLesserThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").lesserThanDateTime("2019-02-28 22:00:10"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime < '2019-02-28T22:00:10'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerLesserThan() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanInteger(31))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age < 31", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionLongLesserThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").lesserThanLong(9223372036854775806L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash < 9223372036854775806", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleLesserThan() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").lesserThanDouble(25.7))
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
                                .where(valueOf("age").greaterThanInteger(20))))
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
                                .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname < ALL (SELECT lastname FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionStringLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("firstname").lesserThanOrEqualString("Miika"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname <= 'Miika'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 3);
    }

    @Test
    public void testConditionDateLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").lesserThanOrEqualDate("2019-01-02"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate <= '2019-01-02'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").lesserThanOrEqualDateTime("2019-01-02 22:02:02"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime <= '2019-01-02T22:02:02'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").lesserThanOrEqualInteger(30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age <= 30", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionLongLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").lesserThanOrEqualLong(9223372036854775806L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash <= 9223372036854775806", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionDoubleLesserThanOrEqual() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").lesserThanOrEqualDouble(25.6))
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
                                .where(valueOf("age").greaterThanInteger(20))))
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
                                .where(valueOf("age").greaterThanInteger(20))))
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
                .where(valueOf("firstname").isBetweenString("Ma", "Mi"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE firstname BETWEEN 'Ma' AND 'Mi'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateBetween() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").isBetweenDate("2019-01-01", "2021-01-01"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate BETWEEN '2019-01-01' AND '2021-01-01'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDateTimeBetween() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").isBetweenDateTime("2019-01-01 03:03:03", "2021-01-01 04:04:04"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime BETWEEN '2019-01-01T03:03:03' AND '2021-01-01T04:04:04'", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionIntegerBetween() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isBetweenInteger(35, 65))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age BETWEEN 35 AND 65", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 2);
    }

    @Test
    public void testConditionLongBetween() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").isBetweenLong(9223372036854775801L, 9223372036854775807L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash BETWEEN 9223372036854775801 AND 9223372036854775807", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionDoubleBetween() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").isBetweenDouble(18.5, 30.5))
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
                .where(valueOf("lastname").isInString("Somero", "Testinen", "Komero"))
                .build();

        assertEquals("SELECT firstname FROM person WHERE lastname IN ('Somero', 'Testinen', 'Komero')", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfDates() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdate").isInDate("1981-01-01", "2018-01-02", "1983-03-03", "1984-04-04"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdate IN ('1981-01-01', '2018-01-02', '1983-03-03', '1984-04-04')", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfDateTimes() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("newdatetime").isInDateTime("1981-01-01 21:01:01", "2018-01-02 21:12:01", "1983-03-03 21:01:03", "1984-04-04 21:01:04"))
                .build();

        assertEquals("SELECT * FROM all_types WHERE newdatetime IN ('1981-01-01T21:01:01', '2018-01-02T21:12:01', '1983-03-03T21:01:03', '1984-04-04T21:01:04')", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfIntegers() throws SQLException {
        SelectQuery query = this.baseQuery
                .where(valueOf("age").isInInteger(18, 19, 20, 21, 30))
                .build();

        assertEquals("SELECT firstname FROM person WHERE age IN (18, 19, 20, 21, 30)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfLong() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("hash").isInLong(9223372036854775802L, 9223372036854775804L, 9223372036854775806L, 9223372036854775807L))
                .build();

        assertEquals("SELECT * FROM all_types WHERE hash IN (9223372036854775802, 9223372036854775804, 9223372036854775806, 9223372036854775807)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 1);
    }

    @Test
    public void testConditionInListOfDoubles() throws SQLException {
        SelectQuery query = this.allTypesBaseQuery
                .where(valueOf("age").isInDouble(18.1, 19.2, 20.3, 25.6))
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
                    .where(valueOf("age").lesserThanInteger(20))))
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
                        .where(valueOf("age").greaterThanInteger(20))))
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
                        .where(valueOf("age").greaterThanInteger(20))))
                .build();

        assertEquals("SELECT firstname FROM person WHERE NOT EXISTS (SELECT * FROM student WHERE age > 20)", query.toString());

        List<Row> result = query.execute();

        assertRowCount(result, 0);
    }
}
