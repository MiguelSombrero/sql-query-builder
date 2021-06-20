package builder.query.insert;

import query.InsertQuery;

public class Value extends ValueTemplate {

    public Value(InsertQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        query.append(", ");
    }
}
