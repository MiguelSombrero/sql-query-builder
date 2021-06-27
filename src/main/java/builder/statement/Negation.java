package builder.statement;

import query.Statement;

public class Negation extends Comparison {

    public Negation(Statement query) {
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
        statement.appendFront("NOT ");
        return new Comparison(statement);
    }

}
