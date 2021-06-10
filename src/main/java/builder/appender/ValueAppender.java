package builder.appender;

import database.column.DoubleColumnValue;
import database.column.IntegerColumnValue;
import database.column.StringColumnValue;
import query.Query;

public class ValueAppender {

    public static void appendStringParam(Query query, String value) {
        query.append("?");
        StringColumnValue param = new StringColumnValue(value);
        query.addParam(param);
    }

    public static void appendIntParam(Query query, int value) {
        query.append("?");
        IntegerColumnValue param = new IntegerColumnValue(value);
        query.addParam(param);
    }

    public static void appendDoubleParam(Query query, double value) {
        query.append("?");
        DoubleColumnValue param = new DoubleColumnValue(value);
        query.addParam(param);
    }
}
