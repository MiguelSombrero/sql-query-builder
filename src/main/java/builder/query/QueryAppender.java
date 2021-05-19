package builder.query;

public class QueryAppender {

    public static void appendStringValue(Query query, String value) {
        if (!value.equals("?")) {
            query.append("'");
            query.append(value);
            query.append("'");
        } else {
            query.append(value);
        }
    }

    public static void appendList(Query query, String ...list) {
        query.append("(");
        query.append(list[0]);

        for (int i = 1; i < list.length; i++) {
            query.append(", ");
            query.append(list[i]);
        }

        query.append(")");
    }

    public static void appendListOfValues(Query query, String ...listOfValues) {
        query.append("(");
        appendStringValue(query, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            appendStringValue(query, listOfValues[i]);
        }

        query.append(")");
    }

    public static void appendListOfValues(Query query, int ...listOfValue) {
        query.append("(");
        query.append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            query.append(", ");
            query.append(listOfValue[i]);
        }

        query.append(")");
    }

    public static void appendListOfValues(Query query, double ...listOfValues) {
        query.append("(");
        query.append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            query.append(listOfValues[i]);
        }

        query.append(")");
    }
}
