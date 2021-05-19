package builder.statement.insert;

import builder.query.Query;

public class Value extends ValueTemplate {

    public Value(Query query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        query.append(", ");
    }
}
