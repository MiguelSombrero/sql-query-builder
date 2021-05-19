package builder.statement.insert;

import builder.query.Query;

public class FirstValue extends ValueTemplate {

    public FirstValue(Query query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
