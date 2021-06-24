package builder.query.select.order;

import query.Clause;

public class Orderer extends Limit {

    public Orderer(Clause clause) {
        super(clause);
    }

    /**
     * Appends 'ORDER BY' into query string 'SELECT ... ORDER BY column(s)'.
     *
     * @return FirstOrderBy class which can be used to
     * append 'column(s)' in ORDER BY clause
     */
    public FirstOrderBy orderBy() {
        clause.append(" ORDER BY ");
        return new FirstOrderBy(clause);
    }
}
