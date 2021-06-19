package builder.query.select.order;

import query.SelectQuery;

public class Grouper extends Orderer {

    public Grouper(SelectQuery query) {
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
