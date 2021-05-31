package builder.statement.insert;

import query.SQLQuery;

public class Value extends ValueTemplate {

    public Value(SQLQuery SQLQuery) {
        super(SQLQuery);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        SQLQuery.append(", ");
    }
}
