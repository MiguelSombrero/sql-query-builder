package builder.appender;

import query.Query;

public class ListValueAppender {

    public static void appendListOfStringParams(Query query, String ...listOfValues) {
        query.append("(");
        ValueAppender.appendStringParam(query, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            ValueAppender.appendStringParam(query, listOfValues[i]);
        }

        query.append(")");
    }

    public static void appendListOfIntParams(Query query, int ...listOfValues) {
        query.append("(");
        ValueAppender.appendIntParam(query, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            ValueAppender.appendIntParam(query, listOfValues[i]);
        }

        query.append(")");
    }

    public static void appendListOfDoubleParams(Query query, double ...listOfValues) {
        query.append("(");
        ValueAppender.appendDoubleParam(query, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            ValueAppender.appendDoubleParam(query, listOfValues[i]);
        }

        query.append(")");
    }
}
