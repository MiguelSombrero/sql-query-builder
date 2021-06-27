package builder.query.insert;

import query.Statement;

public class FirstValue extends ValueTemplate {

    public FirstValue(Statement statement) {
        super(statement);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
