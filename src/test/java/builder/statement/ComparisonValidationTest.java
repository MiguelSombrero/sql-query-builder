package builder.statement;

import builder.query.SQLQueryBuilder;
import builder.query.select.table.Table;
import org.junit.Before;
import org.junit.Test;
import testutils.DatabaseConnection;
import testutils.DatabaseTestBaseClass;

import static builder.statement.ConditionStatementBuilder.*;
import static org.junit.Assert.assertEquals;

public class ComparisonValidationTest extends DatabaseTestBaseClass {
    private SQLQueryBuilder SQLQueryBuilder;
    private Table baseQuery;

    @Before
    public void setUpQuery() {
        initializeDatabase();

        SQLQueryBuilder = new SQLQueryBuilder(DatabaseConnection.getH2DataSource());

        this.baseQuery = SQLQueryBuilder
                .select()
                    .column("firstname")
                .from()
                    .table("person");
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
