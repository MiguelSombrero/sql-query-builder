package performance;

import builder.statement.select.column.FirstColumn;
import builder.statement.select.table.Table;
import testutils.DatabaseConnection;
import query.QueryFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static builder.condition.WhereClauseFactory.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PerformanceTest {
    private static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);
    private QueryFactory queryFactory;
    private static final String complexQuery = "SELECT p.firstname AS first, p.lastname AS last, c.name AS course FROM persons AS p, courses AS c LEFT JOIN addresses ON addresses.id = persons.address_id WHERE age > 18 AND age < 65 AND NOT firstname = 'Miika'";
    private static final int times = 100_000;

    @Before
    public void setUp() throws SQLException {
        queryFactory = new QueryFactory(DatabaseConnection.getDataSource());
    }

    @Test
    public void testThatQueriesMatch() {
        assertEquals(complexQuery, buildComplexQuery());
        assertEquals(complexQuery, appendComplexQuery());
        assertEquals(complexQuery, concatComplexQuery());
    }

    @Test
    public void testThatBuilding100KQueriesStaysWithin5Seconds() {
        long start = 0L;
        long end = 0L;

        start = System.currentTimeMillis();
        buildComplexQueryN(times);
        end = System.currentTimeMillis();
        long builderMilliseconds = end - start;

        logger.info(builderMilliseconds + " milliseconds");
        assertTrue(5000 > builderMilliseconds);
    }

    @Test
    public void testThatBuildingOverComplexQueryStaysWithin5Seconds() {
        long start = 0L;
        long end = 0L;

        start = System.currentTimeMillis();
        FirstColumn field = queryFactory.select();

        for (int i = 0; i < times; i++) {
            field.column("test").alias("best");
        }

        Table table = field
                .column("last")
                .from()
                    .table("testing");

        for (int i = 0; i < times; i++) {
            table.leftJoin("test2").on("testing.id", "test2.test_id");
        }

        table.where(valueOf("test").contains("yeah")).build();

        end = System.currentTimeMillis();
        long builderMilliseconds = end - start;
        logger.info(builderMilliseconds + " milliseconds");
        assertTrue(5000 > builderMilliseconds);
    }

    public void comparisonOfConcatenatingMethods() {
        long start = 0L;
        long end = 0L;

        start = System.currentTimeMillis();
        buildComplexQueryN(times);
        end = System.currentTimeMillis();
        long builderMilliseconds = end - start;

        start = System.currentTimeMillis();
        appendComplexQueryN(times);
        end = System.currentTimeMillis();
        long appendMilliseconds = end - start;

        start = System.currentTimeMillis();
        concatComplexQueryN(times);
        end = System.currentTimeMillis();
        long concatMilliseconds = end - start;

        logger.info("builder " + builderMilliseconds);
        logger.info("append: " + concatMilliseconds);
        logger.info("percents: ");

        double percent = builderMilliseconds / concatMilliseconds * 1000 / 1000;
        logger.info( percent + " percent");
    }

    private void buildComplexQueryN(int times) {
        for (int i = 0; i < times; i++) {
            String query = buildComplexQuery();
        }
    }

    private void appendComplexQueryN(int times) {
        for (int i = 0; i < times; i++) {
            String query = appendComplexQuery();
        }
    }

    private void concatComplexQueryN(int times) {
        for (int i = 0; i < times; i++) {
            String query = concatComplexQuery();
        }
    }

    private String buildComplexQuery() {
        return queryFactory
                .select()
                    .column("p.firstname").alias("first")
                    .column("p.lastname").alias("last")
                    .column("c.name").alias("course")
                .from()
                    .table("persons").alias("p")
                    .table("courses").alias("c")
                .leftJoin("addresses").on("addresses.id", "persons.address_id")
                .where(valueOf("age").greaterThan(18)
                        .and(valueOf("age").lesserThan(65))
                        .and(valueOf("firstname").not().equals("Miika")))
                .build()
                .toString();
    }

    private String appendComplexQuery() {
        return "SELECT " +
                "p.firstname AS first, " +
                "p.lastname AS last, " +
                "c.name AS course " +
                "FROM persons AS p, " +
                "courses AS c " +
                "LEFT JOIN addresses " +
                "ON addresses.id = persons.address_id " +
                "WHERE age > 18 " +
                "AND age < 65 " +
                "AND NOT firstname = 'Miika'";
    }

    private String concatComplexQuery() {
        return "SELECT "
                .concat("p.firstname AS first, ")
                .concat("p.lastname AS last, ")
                .concat("c.name AS course ")
                .concat("FROM persons AS p, ")
                .concat("courses AS c ")
                .concat("LEFT JOIN addresses ")
                .concat("ON addresses.id = persons.address_id ")
                .concat("WHERE age > 18 ")
                .concat("AND age < 65 ")
                .concat("AND NOT firstname = 'Miika'");
    }
}
