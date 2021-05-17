package builder.statement.update;

import builder.SQLStringAppender;

public class Value extends SQLStringAppender {

    public Value(StringBuilder queryString) {
        super(queryString);
    }

    protected Column value(String value) {
        validateAndAppendStringValue(value);
        return new Column(this.queryString);
    }

    protected Column value(int value) {
        append(value);
        return new Column(this.queryString);
    }
}
