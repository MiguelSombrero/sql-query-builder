package builder.query.select.order;

import builder.query.SQLQueryBuilder;
import builder.query.select.table.Table;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import static builder.clause.ConditionClauseBuilder.count;

public class OrderValidationTest extends DatabaseTestBaseClass {
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder sQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getDataSource());

        this.baseQuery = sQLQueryBuilder
                .select()
                    .all()
                .from()
                    .table("person");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGroupByHavingWithSQLInjection() {
        this.baseQuery.groupBy()
                .column("age")
                .having(count(";DROP").equals(100))
                .build();
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithSQLInjection() {
        baseQuery
                .groupBy()
                .column(";DROP")
                .build();
    }

    @Test(expected =IllegalArgumentException.class)
    public void testGroupByWithHavingColumnSQLInjection() {
        baseQuery
                .groupBy()
                    .column("firstname")
                    .column(";DROP")
                .build();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testOrderByFirstColumnWithSQLInjection() {
        baseQuery
                .orderBy()
                .column(";DROP").asc()
                .build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testOrderByColumnWithSQLInjection() {
        baseQuery
                .orderBy()
                .column("age")
                .column(";DROP").asc()
                .build();

    }
}
