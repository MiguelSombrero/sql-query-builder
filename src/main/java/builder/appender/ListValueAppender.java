package builder.appender;

import clause.Clause;

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

    public static void appendListOfIntParams(Clause clause, int ...listOfValues) {
        clause.append("(");
        ValueAppender.appendIntParam(clause, listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            clause.append(", ");
            ValueAppender.appendIntParam(clause, listOfValues[i]);
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
