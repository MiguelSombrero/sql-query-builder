package builder.query.insert;

import query.Statement;

public class Value extends ValueTemplate {

    public Value(Statement statement) {
        super(statement);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        statement.append(", ");
    }
}
