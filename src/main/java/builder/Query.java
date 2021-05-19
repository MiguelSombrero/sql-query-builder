package builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Query {
    protected static Logger logger = LoggerFactory.getLogger(Query.class);

    private StringBuilder queryString;

    public Query(StringBuilder queryString) {
        this.queryString = queryString;
    }

    public String build() {
        return queryString.toString();
    }

    public void append(String value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(int value) {
        this.queryString = this.queryString.append(value);
    }

    public void append(double value) {
        this.queryString = this.queryString.append(value);
    }

    public void insert(int index, String value) {
        this.queryString = this.queryString.insert(index, value);
    }

    public void appendStringValue(String value) {
        if (!value.equals("?")) {
            append("'");
            append(value);
            append("'");
        } else {
            append(value);
        }
    }

    public void appendList(String ...list) {
        append("(");
        append(list[0]);

        for (int i = 1; i < list.length; i++) {
            append(", ");
            append(list[i]);
        }

        append(")");
    }

    public void appendListOfValues(String ...listOfValues) {
        append("(");
        appendStringValue(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            appendStringValue(listOfValues[i]);
        }

        append(")");
    }

    public void appendListOfValues(int ...listOfValue) {
        append("(");
        append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            append(", ");
            append(listOfValue[i]);
        }

        append(")");
    }

    public void appendListOfValues(double ...listOfValues) {
        append("(");
        append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            append(listOfValues[i]);
        }

        append(")");
    }
}
