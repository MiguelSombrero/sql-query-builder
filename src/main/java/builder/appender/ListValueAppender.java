package builder.appender;

import query.Query;
import validation.Validator;
import validation.ValidatorFactory;

public class ListValueAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

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

    private static void appendList(Query query, String ...list) {
        query.append("(");
        query.append(list[0]);

        for (int i = 1; i < list.length; i++) {
            query.append(", ");
            query.append(list[i]);
        }

        query.append(")");
    }
}
