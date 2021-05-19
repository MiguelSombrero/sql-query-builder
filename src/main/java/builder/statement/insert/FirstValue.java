package builder.statement.insert;

import builder.Query;

public class FirstValue extends ValueTemplate {

    public FirstValue(Query query) {
        super(query);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
