package builder.statement.insert;

import query.dml.InsertQuery;

public class FirstValue extends ValueTemplate {

    public FirstValue(InsertQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
