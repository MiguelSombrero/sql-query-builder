package builder.statement.insert;

import query.dml.InsertQuery;

public class Value extends ValueTemplate {

    public Value(InsertQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        query.append(", ");
    }
}
