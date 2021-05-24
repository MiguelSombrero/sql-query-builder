package builder.statement.update;

import builder.Query;
import builder.utils.StringValueAppender;

public class Value {
    private StringValueAppender stringValueAppender;
    private Query query;

    public Value(Query query) {
        this.query = query;
        this.stringValueAppender = new StringValueAppender(query);
    }

    public Column value(String value) {
        stringValueAppender.validateAndAppend(value);
        return new Column(query);
    }

    public Column value(int value) {
        query.append(value);
        return new Column(query);
    }

    public Column value(double value) {
        query.append(value);
        return new Column(query);
    }
}
