package builder.query.insert;

import query.Clause;

public class FirstValue extends ValueTemplate {

    public FirstValue(Clause clause) {
        super(clause);
    }

    @Override
    protected void addCommaAfterFirstValue() {
    }
}
