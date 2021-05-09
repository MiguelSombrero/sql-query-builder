package builder.statement.update;

import builder.SQLStringBuilder;

public class Value extends SQLStringBuilder {

    public Value(StringBuilder builder) {
        super(builder);
    }

    protected Column value(String value) {
        append("'");
        append(value);
        append("'");
        return new Column(this.builder);
    }

    protected Column value(int value) {
        append(value);
        return new Column(this.builder);
    }
}
