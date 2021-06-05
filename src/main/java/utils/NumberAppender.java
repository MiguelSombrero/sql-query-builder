package utils;

import query.Query;

public class NumberAppender {

    private Query query;

    public NumberAppender(Query query) {
        this.query = query;
    }

    public void appendListOfValues(int ...listOfValue) {
        query.append("(");
        query.append(listOfValue[0]);

        for (int i = 1; i < listOfValue.length; i++) {
            query.append(", ");
            query.append(listOfValue[i]);
        }

        query.append(")");
    }

    public void appendListOfValues(double ...listOfValues) {
        query.append("(");
        query.append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            query.append(", ");
            query.append(listOfValues[i]);
        }

        query.append(")");
    }
}
