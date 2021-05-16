package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.DateValidator;
import validation.InputValidator;
import validation.StringValidator;

import javax.xml.bind.ValidationException;

public abstract class SQLStringAppender {
    protected static Logger logger = LoggerFactory.getLogger(SQLStringAppender.class);

    private static InputValidator validator = new InputValidator(
            new StringValidator(), new DateValidator());

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

    protected void validateAndAppend(String value) throws ValidationException {
        validator.validOrThrow(value);
        this.queryString = this.queryString.append(value);
    }

    protected void validateAndAppendStringValue(String value) throws ValidationException {
        append("'");
        validateAndAppend(value);
        append("'");
    }

    protected void validateAndAppendList(String ...listOfValues) throws ValidationException {
        append("(");
        validateAndAppend(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            validateAndAppend(listOfValues[i]);
        }

        append(")");
    }

    protected void validateAndAppendListOfValues(String ...listOfValues) throws ValidationException {
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
