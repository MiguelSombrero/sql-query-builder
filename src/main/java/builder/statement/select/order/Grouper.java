package builder.statement.select.order;

import builder.Query;

public class Grouper extends Orderer {

    public Grouper(Query query) {
        super(query);
    }

    /**
     * Appends 'GROUP BY' into query string 'SELECT ... GROUP BY column(s)'.
     *
     * @return GroupBy class which can be used to
     * append 'column(s)' in GROUP BY clause
     */
    public GroupBy groupBy() {
        query.append(" GROUP BY ");
        return new GroupBy(query);
    }
}
