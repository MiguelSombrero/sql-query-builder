package builder.statement.insert;

import query.dml.DMLQuery;

public class FirstValue extends ValueTemplate {

    public FirstValue(DMLQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
