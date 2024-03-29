package builder.appender;

import query.Clause;

public class ListValueAppender {

    public static void appendListOfStringParams(Clause clause, String ...listOfValues) {
        clause.append("(");
        ValueAppender.appendStringParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendStringParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }

    public static void appendListOfDateParams(Clause clause, String ...listOfValues) {
        clause.append("(");
        ValueAppender.appendDateParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendDateParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }

    public static void appendListOfTimestampParams(Clause clause, String ...listOfValues) {
        clause.append("(");
        ValueAppender.appendTimestampParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendTimestampParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }

    public static void appendListOfIntParams(Clause clause, int ...listOfValues) {
        clause.append("(");
        ValueAppender.appendIntParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendIntParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }

    public static void appendListOfLongParams(Clause clause, long ...listOfValues) {
        clause.append("(");
        ValueAppender.appendLongParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendLongParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }

    public static void appendListOfDoubleParams(Clause clause, double ...listOfValues) {
        clause.append("(");
        ValueAppender.appendDoubleParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendDoubleParam(clause, listOfValues[i]);
        }

        clause.append(")");
    }
}
