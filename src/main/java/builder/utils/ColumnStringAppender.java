package builder.utils;

import factory.ValidatorFactory;
import query.Query;
import validation.Validator;

public class ColumnStringAppender {
    private static Validator validator = ValidatorFactory.exceptionThrowingColumnValidator();

    private Query query;

    public ColumnStringAppender(Query query) {
        this.query = query;
    }

    public void validateAndAppend(String value) {
        validate(value);
        query.append(value);
    }

    private void validate(String value) {
        validator.validate(value);
    }
}