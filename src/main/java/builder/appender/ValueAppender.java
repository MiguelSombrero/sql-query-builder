package builder.appender;

import database.column.*;
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

    public static void appendDateParam(Query query, String value) {
        query.append("?");
        DateColumnValue param = new DateColumnValue(value);
        query.addParam(param);
    }

    public static void appendDateTimeParam(Query query, String value) {
        query.append("?");
        DateTimeColumnValue param = new DateTimeColumnValue(value);
        query.addParam(param);
    }
}
