package builder.query.select.order;

import query.Clause;

public class Grouper extends Orderer {

    public Grouper(Clause clause) {
        super(clause);
    }

    /**
     * Appends 'GROUP BY' into query string 'SELECT ... GROUP BY column(s)'.
     *
     * @return GroupBy class which can be used to
     * append 'column(s)' in GROUP BY clause
     */
    public GroupBy groupBy() {
        clause.append(" GROUP BY ");
        return new GroupBy(clause);
    }
}
