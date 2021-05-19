package builder.condition;

import builder.query.Query;

public class Negation extends Comparison {

    public Negation(Query query) {
        super(query);
    }

    public Comparison not() {
        query.insert(0, "NOT ");
        return new Comparison(query);
    }

}
