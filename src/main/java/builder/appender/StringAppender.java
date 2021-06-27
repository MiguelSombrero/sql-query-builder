package builder.appender;

import query.Statement;
import validation.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringAppender {
    private static Validator<String> validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static void validateAndAppend(Statement statement, String value) {
        validate(value);
        statement.append(value);
    }

    public static void validateAndAppendList(Statement statement, String ...listOfValues) {
        validateList(listOfValues);
        appendList(statement, listOfValues);
    }

    private static void validate(String value) {
        validator.validate(value);
    }

    private static void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }

    private static void appendList(Statement statement, String ...list) {
        statement.append("(");
        statement.append(list[0]);

        for (int i = 1; i < list.length; i++) {
            statement.append(", ");
            statement.append(list[i]);
        }

        statement.append(")");
    }
}
