package builder.appender;

import query.Query;
import validation.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringAppender {
    private static Validator<String> validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static void validateAndAppend(Query query, String value) {
        validate(value);
        query.append(value);
    }

    public static void validateAndAppendList(Query query, String ...listOfValues) {
        validateList(listOfValues);
        appendList(query, listOfValues);
    }

    private static void validate(String value) {
        validator.validate(value);
    }

    private static void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
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
