package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLStringAppender {
    protected static Logger logger = LoggerFactory.getLogger(SQLStringAppender.class);

    protected StringBuilder queryString;

    public SQLStringAppender(StringBuilder queryString) {
        this.queryString = queryString;
    }

    protected void append(String text) {
        this.queryString = this.queryString.append(text);
    }

    protected void append(Integer value) {
        this.queryString = this.queryString.append(value);
    }

    protected void append(Double value) {
        this.queryString = this.queryString.append(value);
    }

    protected void appendStringValue(String value) {
        append("'");
        append(value);
        append("'");
    }

    protected void appendList(String ...listOfValues) {
        append("(");
        append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            append(listOfValues[i]);
        }

        append(")");
    }

    protected void appendListOfValues(String ...listOfValues) {
        append("(");
        appendStringValue(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            appendStringValue(listOfValues[i]);
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
