package performance;

import builder.query.SQLQueryBuilder;
import builder.query.select.column.FirstColumn;
import builder.query.select.table.Table;
import testutils.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static builder.clause.ConditionClauseBuilder.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PerformanceTest {
    private static Logger logger = LoggerFactory.getLogger(PerformanceTest.class);

    private SQLQueryBuilder sqlQueryBuilder;
    private static final int TIMES = 100_000;
    private static final int TIME_LIMIT = 5000;

    @Before
    public void setUp() throws SQLException {
        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());
    }

    @Test
    public void testThatBuilding100KQueriesStaysWithinTimeLimit() {
        long start = System.currentTimeMillis();
        buildComplexQueryN(TIMES);
        long end = System.currentTimeMillis();

        long builderMilliseconds = end - start;

        logger.info("build complex query " + TIMES + " times in " + builderMilliseconds + " milliseconds");
        assertTrue(TIME_LIMIT > builderMilliseconds);
    }

    @Test
    public void testThatBuildingOverComplexQueryStaysWithinTimeLimit() {
        long start = System.currentTimeMillis();
        FirstColumn field = sqlQueryBuilder.select();

        for (int i = 0; i < TIMES; i++) {
            field.column("test").alias("best");
        }

        Table table = field
                .column("last")
                .from()
                    .table("testing");

        for (int i = 0; i < TIMES; i++) {
            table.leftJoin("test2").on("testing.id", "test2.test_id");
        }

        table.where(valueOf("test").contains("yeah")).build();

        long end = System.currentTimeMillis();

        long builderMilliseconds = end - start;

        logger.info("building one too complex query in " + builderMilliseconds + " milliseconds");
        assertTrue(TIME_LIMIT > builderMilliseconds);
    }

    private void buildComplexQueryN(int times) {
        for (int i = 0; i < times; i++) {
            String query = buildComplexQuery();
        }
    }

    private String buildComplexQuery() {
        return sqlQueryBuilder
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
                        .and(valueOf("firstname").not().stringEquals("Miika")))
                .build()
                .toString();
    }
}
