package builder.statement.insert;

import query.DMLQuery;

public class Value extends ValueTemplate {

    public Value(DMLQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        query.append(", ");
    }
}
