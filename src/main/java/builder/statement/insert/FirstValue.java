package builder.statement.insert;

import query.DMLQuery;

public class FirstValue extends ValueTemplate {

    public FirstValue(DMLQuery query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
