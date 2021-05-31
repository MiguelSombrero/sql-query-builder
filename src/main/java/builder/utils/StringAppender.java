package builder.utils;

import query.Query;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingNameValidator();

    private Query query;

    public StringAppender(Query query) {
        this.query = query;
    }

    public void validateAndAppend(String value) {
        validate(value);
        query.append(value);
    }

    public void validateAndAppendList(String ...listOfValues) {
        validateList(listOfValues);
        appendList(listOfValues);
    }

    private void validate(String value) {
        validator.validate(value);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(this::validate);
    }

    private void appendList(String ...list) {
        query.append("(");
        query.append(list[0]);

        for (int i = 1; i < list.length; i++) {
            query.append(", ");
            query.append(list[i]);
        }

        query.append(")");
    }
}
