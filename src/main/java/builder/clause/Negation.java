package builder.clause;

import query.Clause;

public class Negation extends Comparison {

    public Negation(Clause query) {
        super(query);
    }

    /**
     * Appends 'NOT' into 'WHERE NOT condition' clause.
     *
     * @return Condition class which can be used to append
     * more conditions with AND, OR and OR (...) operators,
     * or terminate query building
     */
    public Comparison not() {
        clause.appendFront("NOT ");
        return new Comparison(clause);
    }

}
