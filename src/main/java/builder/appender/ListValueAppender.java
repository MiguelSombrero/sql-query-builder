package builder.appender;

import query.Statement;

public class ListValueAppender {

    public static void appendListOfStringParams(Statement statement, String ...listOfValues) {
        statement.append("(");
        ValueAppender.appendStringParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendStringParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }

    public static void appendListOfDateParams(Statement statement, String ...listOfValues) {
        statement.append("(");
        ValueAppender.appendDateParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendDateParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }

    public static void appendListOfTimestampParams(Statement statement, String ...listOfValues) {
        statement.append("(");
        ValueAppender.appendTimestampParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendTimestampParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }

    public static void appendListOfIntParams(Statement statement, int ...listOfValues) {
        statement.append("(");
        ValueAppender.appendIntParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendIntParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }

    public static void appendListOfLongParams(Statement statement, long ...listOfValues) {
        statement.append("(");
        ValueAppender.appendLongParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendLongParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }

    public static void appendListOfDoubleParams(Statement statement, double ...listOfValues) {
        statement.append("(");
        ValueAppender.appendDoubleParam(statement, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            statement.append(", ");
            ValueAppender.appendDoubleParam(statement, listOfValues[i]);
        }

        statement.append(")");
    }
}
