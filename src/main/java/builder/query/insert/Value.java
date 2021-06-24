package builder.query.insert;

import query.Clause;

public class Value extends ValueTemplate {

    public Value(Clause clause) {
        super(clause);
    }

    @Override
    protected void addCommaAfterFirstValue() {
        clause.append(", ");
    }
}
