package builder.statement.insert;

import query.SQLQuery;

public class FirstValue extends ValueTemplate {

    public FirstValue(SQLQuery SQLQuery) {
        super(SQLQuery);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
