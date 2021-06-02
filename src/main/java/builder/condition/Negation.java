package builder.condition;

import query.Query;

public class Negation extends Comparison {

    public Negation(Query query) {
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
        query.insert(0, "NOT ");
        return new Comparison(query);
    }

}
