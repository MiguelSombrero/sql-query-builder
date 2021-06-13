package builder.appender;

import clause.Clause;
import validation.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringAppender {
    private static Validator<String> validator = ValidatorFactory.exceptionThrowingNameValidator();

    public static void validateAndAppend(Clause clause, String value) {
        validate(value);
        clause.append(value);
    }

    public static void validateAndAppendList(Clause clause, String ...listOfValues) {
        validateList(listOfValues);
        appendList(clause, listOfValues);
    }

    private static void validate(String value) {
        validator.validate(value);
    }

    private static void validateList(String[] columns) {
        Arrays.stream(columns).forEach(column -> validator.validate(column));
    }

    private static void appendList(Clause clause, String ...list) {
        clause.append("(");
        clause.append(list[0]);

        for (int i = 1; i < list.length; i++) {
            clause.append(", ");
            clause.append(list[i]);
        }

        clause.append(")");
    }
}
