package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SQLQuery {
    protected static Logger logger = LoggerFactory.getLogger(SQLQuery.class);

    protected StringBuilder queryString;

    public SQLQuery(StringBuilder queryString) {
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

    protected void appendStringValue(String value) {
        if (!value.equals("?")) {
            append("'");
            append(value);
            append("'");
        } else {
            append(value);
        }
    }

    protected void appendList(String ...list) {
        append("(");
        append(list[0]);

        for (int i = 1; i < list.length; i++) {
            append(", ");
            append(list[i]);
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
