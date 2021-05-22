package builder.statement.select.order;

import builder.query.Query;

public class Orderer extends Limit {

    public Orderer(Query query) {
        super(query);
    }

    /**
     * Appends 'ORDER BY' into query string 'SELECT ... ORDER BY column(s)'.
     *
     * @return FirstOrderBy class which can be used to
     * append 'column(s)' in ORDER BY clause
     */
    public FirstOrderBy orderBy() {
        query.append(" ORDER BY ");
        return new FirstOrderBy(query);
    }
}
