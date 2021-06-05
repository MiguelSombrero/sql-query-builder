package utils;

import query.Query;
import validation.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class StringValueAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingStringValueValidator();

    private Query query;

    public StringValueAppender(Query query) {
        this.query = query;
    }

    public void validateAndAppendListOfValues(String ...listOfValues) {
        validateList(listOfValues);
        appendListOfValues(listOfValues);
    }

    public void validateAndAppendStringValue(String value) {
        validate(value);
        appendStringValue(value);
    }

    public void appendStringValue(String value) {
        query.append("?");
        query.addParam(value);
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
