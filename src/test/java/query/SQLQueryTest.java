package query;

import org.junit.Test;

public class SQLQueryTest {

    @Test
    public void addParamsTest() {
        SQLQuery sqlQuery = new SQLQuery(new StringBuilder());
        sqlQuery.addParam("Miika");
        sqlQuery.addParam(40);
        sqlQuery.addParam("Lassi Kukkonen");


    }
}
