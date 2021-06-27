package builder.query.select.order;

import query.Statement;

public class Grouper extends Orderer {

    public Grouper(Statement statement) {
        super(statement);
    }

    /**
     * Appends 'GROUP BY' into query string 'SELECT ... GROUP BY column(s)'.
     *
     * @return GroupBy class which can be used to
     * append 'column(s)' in GROUP BY clause
     */
    public GroupBy groupBy() {
        statement.append(" GROUP BY ");
        return new GroupBy(statement);
    }
}
