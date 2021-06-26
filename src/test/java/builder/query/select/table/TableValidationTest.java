package builder.query.select.table;

import builder.query.SQLQueryBuilder;
import builder.query.select.column.ToFrom;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;


public class TableValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder sqlQueryBuilder;
    private ToFrom baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        sqlQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = sqlQueryBuilder
                .select()
                    .all();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableWithSQLInjection() {
        this.baseQuery
                .from()
                .table(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectTableAliasWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person").alias(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectSubQueryAliasWithSQLInjection() {
        this.baseQuery
                .from()
                .sub(sqlQueryBuilder
                        .select()
                            .column("*")
                        .from()
                            .table("person")
                )
                .alias(";DROP")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin(";DROP").alias("a").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableAliasWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias(";DROP").on("person.id", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias("a").on(";DROP", "a.person_id")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSelectJoinTableOnJoinWithSQLInjection() {
        this.baseQuery
                .from()
                .table("person")
                .leftJoin("address").alias("a").on("person.id", ";DROP")
                .build();
    }
}
