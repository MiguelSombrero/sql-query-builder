package builder.utils;

import builder.Query;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringValueAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    private Query query;

    public StringValueAppender(Query query) {
        this.query = query;
    }

    public void appendStringValue(String value) {
        if (!value.equals("?")) {
            query.append("'");
            query.append(value);
            query.append("'");
        } else {
            query.append(value);
        }
    }

    public void validateAndAppendListOfValues(String ...listOfValues) {
        validateList(listOfValues);
        appendListOfValues(listOfValues);
    }

    public void validateAndAppend(String value) {
        validate(value);
        appendStringValue(value);
    }

    public void validate(String value) {
        validator.validate(value);
    }

    private void validateList(String[] columns) {
        Arrays.stream(columns).forEach(this::validate);
    }

    public void appendListOfValues(String ...listOfValues) {
        query.append("(");
        appendStringValue(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            appendStringValue(listOfValues[i]);
        }

        query.append(")");
    }
}
