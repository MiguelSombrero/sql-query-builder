package builder;

import factory.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.*;

public abstract class SQLStringAppender {
    protected static Logger logger = LoggerFactory.getLogger(SQLStringAppender.class);

    private Validator validator = ValidatorFactory.exceptionThrowingStringOrDateValidator();

    protected StringBuilder queryString;

    public SQLStringAppender(StringBuilder queryString) {
        this.queryString = queryString;
    }

    protected void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    protected void append(int value) {
        this.queryString = this.queryString.append(value);
    }

    protected void append(double value) {
        this.queryString = this.queryString.append(value);
    }

    protected void validateAndAppend(String value) {
        validator.validate(value);
        this.queryString = this.queryString.append(value);
    }

    protected void validateAndAppendStringValue(String value) {
        append("'");
        validateAndAppend(value);
        append("'");
    }

    protected void validateAndAppendList(String ...listOfValues) {
        append("(");
        validateAndAppend(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            validateAndAppend(listOfValues[i]);
        }

        append(")");
    }

    protected void validateAndAppendListOfValues(String ...listOfValues) {
        append("(");
        validateAndAppendStringValue(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            validateAndAppendStringValue(listOfValues[i]);
        }

        append(")");
    }

    protected void appendListOfValues(int ...listOfValue) {
        append("(");
        append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            append(listOfValue[i]);
        }

        append(")");
    }

    protected void appendListOfValues(double ...listOfValues) {
        append("(");
        append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            append(listOfValues[i]);
        }

        append(")");
    }
}
